# 🚀 Initial Data Setup for Crypto Trading App

This document describes the initial data setup for the application's database. It includes pre-filled records for user accounts and their crypto wallets to simulate trading activity.

---

## 🌐 API Access

### ▶️ Local Server URL
- **Base URL:** [http://localhost:8080](http://localhost:8080)

### 📘 Swagger UI
- Use Swagger UI to explore and test the API endpoints.
- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📘 Tables Involved

### 1. `user_account`
This table stores information about user balances.

| Column             | Description                         |
|--------------------|-------------------------------------|
| `id`               | Auto-incremented unique identifier  |
| `balance`          | Available account balance (default: 50,000.0000) |
| `created_date`     | Timestamp of record creation        |
| `last_modified_date`| Timestamp of last modification     |
| `created_by`       | (Optional) Who created the record   |
| `last_modified_by` | (Optional) Who last modified the record |

#### ✅ Seeded Data:
- **10 user accounts**
- Each with a default balance of **50,000.0000**

---

### 2. `wallet`
This table stores each user's cryptocurrency holdings.

| Column             | Description                             |
|--------------------|-----------------------------------------|
| `id`               | Auto-incremented unique identifier      |
| `user_id`          | Reference to `user_account.id`          |
| `symbol`           | Crypto trading pair (e.g., ETHUSDT)     |
| `quantity`         | Amount of that crypto the user owns     |
| `created_date`     | Timestamp of record creation            |
| `last_modified_date`| Timestamp of last modification         |
| `created_by`       | (Optional) Who created the record       |
| `last_modified_by` | (Optional) Who last modified the record |

#### ✅ Seeded Data:
- **Each of the 10 users** is given:
  - `ETHUSDT`: 10.0000 units
  - `BTCUSDT`: 2.0000 units
- Total of **20 wallet records**

---

## 📊 Sample Data Summary

| User ID | Balance   | ETHUSDT | BTCUSDT |
|---------|-----------|---------|---------|
| 1       | 50000.0000 | 10.0000 | 2.0000 |
| 2       | 50000.0000 | 10.0000 | 2.0000 |
| ...     | ...       | ...     | ...     |
| 10      | 50000.0000 | 10.0000 | 2.0000 |

---

## 🔧 How to Use

You can use this data to:
- Test trading logic (buy/sell crypto)
- Validate order matching algorithms
- Simulate balance updates

---

## 📌 Notes

- All timestamps are set to the current system time using `CURRENT_TIMESTAMP`.
- Foreign key constraints ensure wallets are always linked to existing users.
- Wallets are **unique per (user_id, symbol)** to prevent duplicates.

---

Feel free to modify or extend this data as needed for testing or demo purposes.
