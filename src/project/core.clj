(ns project.core
  (:gen-class) )

 ;;1.The user wants to get a parameters that makes the calculation easier for him
(defn fn1
  "kv / (kv - 1)  or  kps/(kps-1)"
  [par1]
  (if (= par1 1)
    "Divide by 0!!!"
    (if (< par1 1)
      "Not allowed value"
      (/ par1 (- par1 1)))))

(defn fn2
  "(kv-1)/kv or (kps-1)/kps"
  [par]
  (if (> par 1)
    (/ (- par 1) par)
    "Not allowed value"))

(defn fn3
  "1/(kps-1)"
  [par]
  (if (> par 1)
    (/ 1 (- par 1))
    "Not allowed value"))

(defn fn4
  "(kv-1)/2"
  [par]
  (if (> par 1)
    (/ (- par 1) 2)
    "Not allowed value"))
;;2.The user wants to get the ambient temperature depending on the flight altitude
(defn T0
  [H]
  (if (> H 0)
    (if (> H 11000)
      (- 288.15 (* 11000 0.0065))
      (- 288.15 (* H 0.0065)))
    "Invalid value of flight altitude"))

;;3.The user wants to get the density of the environment depending on the flight altitude
(defn ro0
  [H]
  (if (> H 0)
    (if (<= H 11000)
      (* 1.225 (Math/pow (- 1 (* 0.0065 (/ H 288.15))) 4.2561))
      (* 0.364 (Math/exp (/ (- 11000 H) 6330))))
    "Invalid value of flight altitude"))

;;4.The user wants to get the atmospheric pressure at a specific flight altitude
(defn p0
  [H]
  (if (> H 0)
    (if (> H 11000)
      (* (* 101325 (Math/pow (- 1 (* 0.0065 (/ H 288.15))) 5.2561)) (Math/exp (/ (- 11000 H) 6330)))
      (* 101325 (Math/pow (- 1 (* 0.0065 (/ H 288.15))) 5.2561))) 
      "Invalid value of flight altitude"))

;;5.The user wants to get the speed of sound based on the parameters entered
(defn a0 
[kv Rv T0]
(if (> kv 1)
  (if (> Rv 0)
    (if (> T0 0)
      (Math/sqrt (* kv (* Rv T0)))
      "Incorrect value of the ambient temperature")
    "Incorrect value of the gas constant of air")
  "Incorrect value of the adiabatic constant of air"))

;;Inlet
;;6.User needs Coefficient inlet
(defn tauu
  [kv M0] 
   (if (> kv 1)
     (if (> M0 0)
       (+ 1 (* (fn4 kv) (Math/pow M0 2)))
       "Incorrect value of the Mach number")
     "Incorrect value of the adiabatic constant of air"))

;;7.The user needs a pressure value in the inlet
(defn p0*
  [p0 kv M0]
  (if (> p0 0)
   (if (> kv 1)
     (if (> M0 0)
       (* p0 (Math/pow (tauu kv M0) (fn1 kv)))
       "Incorrect value of the Mach number")
     "Incorrect value of the adiabatic constant of air")
      "Incorrect value of the atmospheric pressure"))  

;;8.The user needs output value of pressure from inlet
(defn p1*
  [p0* sigmau]
  (if (> p0* 0)
    (if (> sigmau 0)
      (* p0 sigmau)
      "Incorrect value of the Coefficient of recovery of the total pressure in the inlet")
    "Incorrect value of the atmospheric pressure"))

;;9.User needs temperature in the inlet
(defn T1*
  [T0 tau]
  (if (> T0* 0)
    (if (> tau 0)
      (* T0 tau)
      "Incorrect value of the Coefficient of temperature")
    "Incorrect value of the ambient temperature"))

;;10.User needs Coefficient of pressure in the inlet
(defn piu
  [tau kv] 
   (if (> tau 0)
     (if (> kv 1)
       (Math/pow tau (fn1 kv))
       "Incorrect value of the adiabatic constant of air")
     "Incorrect value of the Coefficient inlet"))

;;Additional parameters
;;11.User needs Movement speed
(defn v0
[M0 a0]
 (if (> M0 0)
   (if (> a0 0)
     (* M0 a0)
     "Incorrect value of the Speed of sound")
   "Incorrect value of the Mach number"))

;;12.User needs Mach number 
(defn mach-number
[v0 a0]
 (if (> v0 0)
   (if (> a0 0)
     (/ v0 a0)
     "Incorrect value of the Speed of sound")
   "Incorrect value of the Movement speed"))

;;13.User needs Aircraft weight
(defn aircraft-weight
[m g]
(if (> m 0)
  (if (> g 0)
    (* m g)
    "Incorrect value of the gravitational acceleration")
  "Incorrect value of the mass of the aircraft"))

;;14.User needs thrust
(defn thrust
[weight finesse]
(if (> weight 0)
  (if (> finesse 0)
    (/ weight finesse)
    "Incorrect value of the finesse")
  "Incorrect value of the weight of the aircraft"))

;;14.User needs specific thrust
(defn specific-thrust
[thrustt mv]
(if (> thrustt 0)
  (if (> mv 0)
    (/ thrustt mv)
    "Incorrect value of the mass air flow")
  "Incorrect value of the thrust"))

;;System of 3 equations
;;15.User needs degree of heating
(defn degree-of-heating
[pimr c1]
(if (> pimr 0)
  (if (> c1 0)
    (Math/pow (/ pimr c1) 2)
    "Incorrect value of the parameter")
  "Incorrect value of the Available degree of spread in the nozzle"))

;;16.User needs Mass air flow
(defn air-flow
  [v0 ro0 Au]
   (if (> v0 0)
     (if (> ro0 1)
       (if (> Au 0)
         (* v0 (* ro0 Au))
         "Incorrect value of the Cross-sectional area of the grommet")
       "Incorrect value of the Density")
     "Incorrect value of the Movement speed"))

;;17.User needs Constant for easy calculation
(defn easy-calculation
  [Cpps T0 pimr kv]
  (if (> Cpps 0)
    (if (> T0 1)
      (if (> pimr 0)
        (if (> kv 1)
          (Math/sqrt (* 2 (* Cpps (* T0 (- 1 (Math/pow (/ 1 pimr) (fn1 kv)))))))
          "Incorrect value of the adiabatic constant of air")
        "Incorrect value of the Available degree of spread in the nozzle")
      "Incorrect value of the ambient temperature")
    "Incorrect value of the Specific heat capacity of combustion products"))

;;18.User needs Mixing ratio of fuel and air
(defn ratio-fuel-air
  [a b c]
  (if (> a 0)
    (/ (- (Math/sqrt (- (Math/pow b 2) (* 4 (* a c)))) b) (* 2 a))
    "Incorrect value of a"))

;;19.User needs coefficient a
(defn coeff-a
[Hd Cpv T0]
 (if (> Hd 0)
   (if (> Cpv 0)
     (if (> T0 0)
       (/ Hd (* Cpv T0))
       "Incorrect value of the ambient temperature")
     "Incorrect value of the Specific heat capacity of air")
   "Incorrect value of the Lower heating value of fuel"))

;;20.User needs coefficient b
(defn coeff-b
[tau Hd Cpv T0]
(if (> tau 0)
  (if (> Hd 0)
    (if (> Cpv 0)
      (if (> T0 0)
        (+ tau (coeff-a Hd Cpv T0))
        "Incorrect value of the ambient temperature")
      "Incorrect value of the Specific heat capacity of air")
    "Incorrect value of the Lower heating value of fuel")
  "Incorrect value of the Coefficient inlet"))

;;21.User needs coefficient c
(defn coeff-c
  [tau v0 fsp c1]
  (if (> tau 0)
    (if (> v0 0)
      (if (> fsp 0)
        (if (> c1 0)
          (+ tau (coeff-a Hd Cpv T0))
          "Incorrect value of the constant")
        "Incorrect value of the Specific thrust")
      "Incorrect value of the movement speed")
    "Incorrect value of the Coefficient inlet"))