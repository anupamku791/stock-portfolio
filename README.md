# Portfolio Application: Phase 1

**Objective:**

Build a stock portfolio application with the following features:

* **Trade API:**
    * Record buy/sell trades for stocks.
    * **Input:**
        * UserAccountId
        * Trade Type (Buy / Sell)
        * Quantity
        * StockID
    * **Output:**
        * Status (Success/Failure)
        * Message (If failure, reason)
* **Stock Details API:**
    * Retrieve details of a specific stock.
    * **Input:**
        * StockID
    * **Output:**
        * Stock Details:
            * Stock ID
            * Stock Name
            * Prices (Open, Close, High, Low, Settlement Price, etc.)
* **Portfolio API:**
    * Retrieve a user's current portfolio holdings.
    * **Input:**
        * UserID
    * **Output:**
        * Holdings:
            * Stock Name
            * Stock ID
            * Quantity
            * Buy Price
            * Current Price
            * Gain / Loss
        * Total Portfolio Holding
        * Total Buy Price
        * Total P/L and P/L %
* **Housekeeping API:**
    * Update stock data using a CSV file.
    * **Input:**
        * CSV file with stock details.

**API Implementation Notes:**

* Use appropriate HTTP methods (POST, GET).
* Design clear and concise endpoints.
* Implement robust business logic and error handling.
* Ensure proper code structure and readability.
* **Note:** Live pricing is not required for this phase.

**Housekeeping API (Update Stocks):**

* Utilize a CSV file (e.g., NSE bhavcopy) for updating stock data.
* Parse the CSV file and process the data.
* Store the updated stock data in the application.

This markdown outline provides a clear and concise overview of the Portfolio Application, its features, and the required functionalities.
