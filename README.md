# Monte Carlo Simulation
1. This mocroservice is to calculate monte carlo for portfolios. It has one end-point:
 
- POST: /monte-carlo

    - Sample request:
    ```json
    [
        {
            "portfolioType": "aggressinve",
            "initialAmount": 100000,
            "mean": 0.094324,
            "sd": 0.15675,
            "inflation": 0.035,
            "numberOfSimulations": 10000,
            "periodInYear": 20
        },
        {
            "portfolioType": "veryConservative",
            "initialAmount": 100000,
            "mean": 0.06189,
            "sd": 0.063438,
            "inflation": 0.035,
            "numberOfSimulations": 10000,
            "periodInYear": 20
        }
    ]
    ```

    - Sample response:
    ```json
    [
        {
            "portfolioType": "aggressinve",
            "initialAmount": 100000,
            "mean": 0.094324,
            "sd": 0.15675,
            "inflation": 0.035,
            "numberOfSimulations": 10000,
            "periodInYear": 20,
            "median": 243673.69,
            "tenPercentBestCase": 550216.15,
            "tenPercentWorstCase": 102896.78
        },
        {
            "portfolioType": "veryConservative",
            "initialAmount": 100000,
            "mean": 0.06189,
            "sd": 0.063438,
            "inflation": 0.035,
            "numberOfSimulations": 10000,
            "periodInYear": 20,
            "median": 156757.77,
            "tenPercentBestCase": 221459.64,
            "tenPercentWorstCase": 111773.82
        }
    ]
    ```

2. How to test the program:
- You can test from postman (http://localhost:8088/api/monte-carlo) with the sample request and response above by starting the program by running the file MonteCarloSimulationApplication.java.
- Or you can run the unit tests from the test package.
