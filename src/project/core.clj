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
      (* (* 101325 (Math/pow(- 1 (* 0.0065 (/ H 288.15))) 5.2561)) (Math/exp (/ (- 11000 H) 6330)))
      (* 101325 (Math/pow(- 1 (* 0.0065 (/ H 288.15))) 5.2561))) 
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

 
