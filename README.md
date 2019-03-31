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
            "median": 243673.69956114597,
            "tenPercentBestCase": 550216.1578462558,
            "tenPercentWorstCase": 102896.78533022427
        },
        {
            "portfolioType": "veryConservative",
            "initialAmount": 100000,
            "mean": 0.06189,
            "sd": 0.063438,
            "inflation": 0.035,
            "numberOfSimulations": 10000,
            "periodInYear": 20,
            "median": 156757.77103304403,
            "tenPercentBestCase": 221459.64195315368,
            "tenPercentWorstCase": 111773.8277371144
        }
    ]
    ```

2. How to test the program:
- You can test from postman with the sample request and response above by starting the program by running the file MonteCarloSimulationApplication.java. The service will start on port 8080.
- Or you can run the unit tests controller or service in test package.
