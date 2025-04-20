package com.aquariux.test.cryptocurrencytradingsystem.services.internal.impls;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.TradingType;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.requests.TradingTransactionRequest;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.TradingTransactionResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.CustomPage;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.Price;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.TradingTransaction;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.UserAccount;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.Wallet;
import com.aquariux.test.cryptocurrencytradingsystem.mappers.TradingTransactionMapper;
import com.aquariux.test.cryptocurrencytradingsystem.repositories.PriceRepository;
import com.aquariux.test.cryptocurrencytradingsystem.repositories.TradingTransactionRepository;
import com.aquariux.test.cryptocurrencytradingsystem.repositories.UserAccountRepository;
import com.aquariux.test.cryptocurrencytradingsystem.repositories.WalletRepository;
import com.aquariux.test.cryptocurrencytradingsystem.services.internal.TradingTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TradingTransactionServiceImpl implements TradingTransactionService {

    private final TradingTransactionRepository tradingTransactionRepository;
    private final TradingTransactionMapper tradingTransactionMapper;
    private final PriceRepository priceRepository;
    private final UserAccountRepository userAccountRepository;
    private final WalletRepository walletRepository;

    @Override
    public CustomPage<TradingTransactionResponse> getTradingTransactionByUserId(Pageable pageable) {
        String userIdStr = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<TradingTransaction> orderBookPage = tradingTransactionRepository.findByUserId(Long.parseLong(userIdStr), pageable);
        return tradingTransactionMapper.toDTOs(orderBookPage);
    }

    @Override
    @Transactional
    public void doTrading(TradingTransactionRequest tradingTransactionRequest) {
        String userIdStr = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = Long.parseLong(userIdStr);

        UserAccount userAccount = userAccountRepository.findById(userId).orElse(null);
        if (userAccount == null) {
            throw new IllegalArgumentException("User not found for id: " + userId);
        }

        Price price = priceRepository.findBySymbol(tradingTransactionRequest.getSymbol());
        if (price == null) {
            throw new IllegalArgumentException("Price not found for symbol: " + tradingTransactionRequest.getSymbol());
        }

        // init wallet if needed
        Wallet wallet = walletRepository.findByUserIdAndSymbol(userId, tradingTransactionRequest.getSymbol());
        if (wallet == null) {
            wallet = Wallet
                    .builder()
                    .user(userAccount)
                    .symbol(tradingTransactionRequest.getSymbol())
                    .quantity(BigDecimal.ZERO)
                    .build();
            walletRepository.save(wallet);
        }

        TradingType tradingType = tradingTransactionRequest.getTradingType();
        switch (tradingType) {
            case BUY -> doBuyTrading(userAccount, wallet, price, tradingTransactionRequest);
            case SELL -> doSellTrading(userAccount, wallet, price, tradingTransactionRequest);
            default -> throw new IllegalArgumentException("Invalid trading type: " + tradingType);
        }
    }

    @Transactional
    protected void doBuyTrading(UserAccount buyerAccount, Wallet buyerWallet, Price price, TradingTransactionRequest tradingTransactionRequest) {
        if (tradingTransactionRequest.getTradingPrice().compareTo(price.getAskPrice()) < 0) {
            throw new IllegalArgumentException("Price is too low for symbol, must be greater than or equal to " + price.getAskPrice());
        }
        TradingTransaction buyingTransaction = TradingTransaction
                .builder()
                .tradingPrice(tradingTransactionRequest.getTradingPrice())
                .tradingType(tradingTransactionRequest.getTradingType())
                .symbol(tradingTransactionRequest.getSymbol())
                .quantity(tradingTransactionRequest.getQuantity())
                .isActive(true)
                .user(buyerAccount)
                .build();

        // find sell transaction, that have price less than or equal to trading price
        List<TradingTransaction> sellingTransactions = tradingTransactionRepository.findTradingTransactionsByTradingTypeAndSymbolAndQuantityIsGreaterThanAndTradingPriceIsLessThanEqualAndUserIsNot(
                TradingType.SELL, tradingTransactionRequest.getSymbol(), BigDecimal.ZERO, tradingTransactionRequest.getTradingPrice(), buyerAccount
        );

        // validate enough quantity
        if (buyerAccount.getBalance().compareTo(buyingTransaction.getQuantity().multiply(buyingTransaction.getTradingPrice())) < 0) {
            throw new IllegalArgumentException("Not enough balance to buy " + buyingTransaction.getQuantity() + " " + buyingTransaction.getSymbol() + " at " + buyingTransaction.getTradingPrice());
        }
        // subtract buyer's balance before, like holding money
        buyerAccount.setBalance(buyerAccount.getBalance().subtract(buyingTransaction.getQuantity().multiply(buyingTransaction.getTradingPrice())));

        for (TradingTransaction sellingTransaction: sellingTransactions) {
            UserAccount sellerAccount = sellingTransaction.getUser();
            if (sellingTransaction.getQuantity().compareTo(buyingTransaction.getQuantity()) >= 0) {
                sellingTransaction.setQuantity(sellingTransaction.getQuantity().subtract(buyingTransaction.getQuantity()));

                buyerWallet.setQuantity(buyerWallet.getQuantity().add(buyingTransaction.getQuantity()));
                sellerAccount.setBalance(sellerAccount.getBalance().add(buyingTransaction.getQuantity().multiply(buyingTransaction.getTradingPrice())));

                buyingTransaction.setQuantity(BigDecimal.ZERO);
                tradingTransactionRepository.save(sellingTransaction);
                break;
            } else {
                buyingTransaction.setQuantity(buyingTransaction.getQuantity().subtract(sellingTransaction.getQuantity()));

                buyerWallet.setQuantity(buyerWallet.getQuantity().add(sellingTransaction.getQuantity()));
                sellerAccount.setBalance(sellerAccount.getBalance().add(sellingTransaction.getQuantity().multiply(sellingTransaction.getTradingPrice())));

                sellingTransaction.setQuantity(BigDecimal.ZERO);
                tradingTransactionRepository.save(sellingTransaction);
            }
        }

        tradingTransactionRepository.save(buyingTransaction);
        walletRepository.save(buyerWallet);
        userAccountRepository.save(buyerAccount);
    }

    @Transactional
    protected void doSellTrading(UserAccount sellerAccount, Wallet sellerWallet, Price price, TradingTransactionRequest tradingTransactionRequest) {
        if (tradingTransactionRequest.getTradingPrice().compareTo(price.getBidPrice()) > 0) {
            throw new IllegalArgumentException("Price is too high for symbol, must be less than or equal to " + price.getBidPrice());
        }
        TradingTransaction sellingTransaction = TradingTransaction
                .builder()
                .tradingPrice(tradingTransactionRequest.getTradingPrice())
                .tradingType(tradingTransactionRequest.getTradingType())
                .symbol(tradingTransactionRequest.getSymbol())
                .quantity(tradingTransactionRequest.getQuantity())
                .isActive(true)
                .user(sellerAccount)
                .build();
        // find buy transaction, that have price greater than or equal to trading price
        List<TradingTransaction> buyingTransactions = tradingTransactionRepository.findTradingTransactionsByTradingTypeAndSymbolAndQuantityIsGreaterThanAndTradingPriceIsGreaterThanEqualAndUserIsNot(
                TradingType.BUY, tradingTransactionRequest.getSymbol(), BigDecimal.ZERO, tradingTransactionRequest.getTradingPrice(), sellerAccount
        );

        // validate enough quantity
        if (sellerWallet.getQuantity().compareTo(sellingTransaction.getQuantity()) < 0) {
            throw new IllegalArgumentException("Not enough " + sellingTransaction.getSymbol() + " to sell " + sellingTransaction.getQuantity() + " at " + sellingTransaction.getTradingPrice());
        }

        // subtract seller's quantity before, like holding quantity
        sellerWallet.setQuantity(sellerWallet.getQuantity().subtract(sellingTransaction.getQuantity()));

        for (TradingTransaction buyingTransaction: buyingTransactions) {
            Wallet buyerWallet = walletRepository.findByUserIdAndSymbol(sellerAccount.getId(), buyingTransaction.getSymbol());
            if (sellingTransaction.getQuantity().compareTo(buyingTransaction.getQuantity()) <= 0) {
                buyingTransaction.setQuantity(buyingTransaction.getQuantity().subtract(sellingTransaction.getQuantity()));

                sellerAccount.setBalance(sellerAccount.getBalance().add(sellingTransaction.getQuantity().multiply(sellingTransaction.getTradingPrice())));
                buyerWallet.setQuantity(buyerWallet.getQuantity().add(sellingTransaction.getQuantity()));

                sellingTransaction.setQuantity(BigDecimal.ZERO);
                tradingTransactionRepository.save(buyingTransaction);
                break;
            } else {
                sellingTransaction.setQuantity(sellingTransaction.getQuantity().subtract(buyingTransaction.getQuantity()));

                sellerAccount.setBalance(sellerAccount.getBalance().add(buyingTransaction.getQuantity().multiply(buyingTransaction.getTradingPrice())));
                buyerWallet.setQuantity(buyerWallet.getQuantity().add(buyingTransaction.getQuantity()));

                buyingTransaction.setQuantity(BigDecimal.ZERO);
                tradingTransactionRepository.save(buyingTransaction);
            }
        }

        tradingTransactionRepository.save(sellingTransaction);
        walletRepository.save(sellerWallet);
        userAccountRepository.save(sellerAccount);
    }
}