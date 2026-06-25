# 📈 AlgoTrade – Java CLI Stock Trading System

A Data Structures & Algorithms (DSA) based Stock Trading Simulator built in Java using multiple advanced data structures such as BST, B-Tree, Segment Tree, Graph, Heap, Trie, Merge Sort, and Quick Sort.

---

## 🚀 Features

### 👨‍💼 Admin Features

* Add New Stocks
* Remove Stocks
* Update Stock Prices
* Display All Stocks
* Search Stocks
* Monitor Market Performance

### 👤 User Features

* User Registration & Login
* Buy Stocks
* Sell Stocks
* Portfolio Management
* Transaction History
* Risk Monitoring

### 📊 Analytics Features

* Sort Stocks by Price (Merge Sort)
* Rank Stocks by Growth (Quick Sort)
* Trading Strategy Recommendations
* Risk Analysis

### 🏭 Sector Analytics & Recommendations

* View Stocks by Sector
* Similar Stock Recommendations
* Top Performing Sectors
* Top Gainers
* Top Losers
* Auto Complete Stock Search

---

# 🧠 Data Structures & Algorithms Used

| Module           | DSA Used              | Purpose                   |
| ---------------- | --------------------- | ------------------------- |
| Stock Storage    | BST                   | Fast Stock Search         |
| Stock Storage    | B-Tree                | Efficient Stock Indexing  |
| Analytics        | Merge Sort            | Price Ranking             |
| Analytics        | Quick Sort            | Growth Ranking            |
| Analytics        | Bubble Sort           | Trading Strategy          |
| Price Analytics  | Segment Tree          | Range Queries             |
| Sector Analytics | Graph                 | Sector-wise Stock Mapping |
| Sector Analytics | Priority Queue (Heap) | Top Gainers & Losers      |
| Sector Analytics | Trie                  | Auto Complete Search      |

---

# 📂 Project Structure

```text
src
│
├── MAIN
├── LOGIN
├── STOCK
├── USER
├── ANALYTICS
├── SECTOR_ANALYTICS
└── UTIL
```

---

# 🏗 Modules

## M1 – Stock Storage & Search

* BST
* B-Tree

## M2 – Sorting & Ranking

* Merge Sort
* Quick Sort

## M3 – Price Range Analytics

* Segment Tree

## M4 – Portfolio Management

## M5 – Trading Operations

## M6 – User & Admin Management

## M7 – Sector Analytics & Recommendations

* Graph
* Heap (Priority Queue)
* Trie

---

# 📊 Sample Stock Data

```text
TCS,4000,4500,4700,Technology
INFY,1800,2100,2200,Technology
HDFC,1500,1700,1800,Banking
ONGC,250,300,320,Oil & Gas
SUNPHARMA,900,1050,1100,Healthcare
```

Format:

```text
StockName,OldPrice,CurrentPrice,HighestPrice,Sector
```

---

# 💻 Sample Output

## Search Stock

```text
Enter Stock Name : TCS

Stock Name      : TCS
Old Price       : ₹4000
Current Price   : ₹4500
Growth %        : +12.50%
Market Status   : BULLISH
```

---

## Sector Recommendations

```text
Selected Stock : TCS

Recommended Stocks:

1. Infosys
2. Wipro
```

---

## Top Gainers

```text
1. TCS        +12.5%
2. Infosys    +10.2%
3. Wipro      +8.1%
```

---

# ⚡ Time Complexity

| Operation          | Complexity |
| ------------------ | ---------- |
| BST Search         | O(log n)   |
| B-Tree Search      | O(log n)   |
| Merge Sort         | O(n log n) |
| Quick Sort         | O(n log n) |
| Segment Tree Query | O(log n)   |
| Heap Insert        | O(log n)   |
| Trie Search        | O(k)       |

Where:

* n = Number of Stocks
* k = Length of Search Prefix

---

# 🛠 Technologies Used

* Java
* OOP Concepts
* File Handling
* Data Structures
* Algorithms
* Command Line Interface (CLI)

---

# 🎯 Learning Outcomes

This project demonstrates practical implementation of:

* Trees (BST, B-Tree)
* Sorting Algorithms
* Segment Trees
* Graphs
* Heaps
* Tries
* File Handling
* Object-Oriented Programming
* CLI Application Development

---

# 👨‍💻 Developed By

**AlgoTrade Team**

A Java-based DSA Project showcasing real-world applications of advanced data structures and algorithms in stock market trading and analytics.
