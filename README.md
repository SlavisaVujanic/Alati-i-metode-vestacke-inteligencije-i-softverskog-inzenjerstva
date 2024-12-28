# Aircraft Performance

This application is used to calculate the performance of aircraft, more precisely their engines, which is very important in mechanical engineering.

## Account Types

This application supports four types of user accounts:

- **Admin**:  
  Admins have the ability to manage user accounts, including adding and deleting accounts, updating passwords, and changing account types.

- **TMM**:  
  TMM users can work exclusively with TMM-type engines.

- **NMM**:  
  NMM users can work exclusively with NMM-type engines.

- **Director**:  
  Directors have access to work with both TMM and NMM types of engines.


## Functions

### Core

1. The multiplication function replaces a large number of functions, whose basic functionality is to multiply numbers
2. The division function replaces a large number of functions, whose basic functionality is to divide two numbers
3. The functionalities (fn1,fn2,fn3,fn4) provide parameter values that, when calculating these performances manually, facilitate the calculation
4. A function (T0) that calculates the ambient temperature depending on altitude
5. A function (ro0) that calculates the density of the environment depending on the flight altitude
6. A function (p0) that calculates the atmospheric pressure at a specific flight altitude
7. The function (a0) calculates the speed of sound depending on the entered parameters.
8. Calculation related to the inlet coefficient (tauu)
9. The user needs a pressure value in the inlet (p0*)
10. Calculate the degree of heating (degree-of-heating)
11. Constant for easier calculation (easy-calculation)
12. Fuel-air mixing ratio at NMM engine (ratio-fuel-air)
13. Coefficient of the quadratic equation (coeff-a)
14. Coefficient of the quadratic equation (coeff-b)
15. Coefficient of the quadratic equation (coeff-c)
16. Determining the convergent coefficient of the inlet (conv-pim)
17. NMM Jet output velocity (vi)
18. Temperature in the compressor (t2-tmm)
19. Fuel-air mixing ratio at TMM engine (tmm-mixing-ratio)
20. Turbine Pi parameter (pi-turbine)
21. Turbine temperature (tmm-t4)
22. Compressor w parameter (wk)
23. Turbine w parameter (wt)
24. Mass parameter at the TMM Jet (mps)
25. TMM Jet output velocity (tmm-vi)
26. Parameter dependent on Mach number (func-M)
27. TMM Jet output A (out-a)
28. Thrust of the TMM engine (tmm-thrust)
29. Specific fuel consumption (tmm-csps)
30. Checking if the value is greater than 1, used later for field validation (greater-than-one?)
31. Parse values from JTextFields (parse)
32. Field validation is performed based on the condition that the entered parameter must be greater than a predefined value (validate-fields)

### Base

1. Function for setting important database-related parameters (create-db-spec)
2. User authentication, depending on the account type, determines further actions in the application (authenticate)
3. Adding users to the database (add-user)
4. Deleting users from the database (delete-user)
5. Updating user data (update-user)
6. List of all users required for the table (get-all-users)

## Tools and Technologies

This project was developed using the following tools and technologies:

- **IntelliJ IDEA Community Edition**  
  This project was developed using IntelliJ IDEA Community Edition, which is free and open-source under the Apache 2.0 license. For more information, visit the [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/).

- **Microsoft SQL Server Express**  
  This project uses Microsoft SQL Server Express Edition, which is free to use under the Microsoft SQL Server Express license. For more details, visit [Microsoft SQL Server Express](https://www.microsoft.com/en-us/sql-server/sql-server-downloads).
