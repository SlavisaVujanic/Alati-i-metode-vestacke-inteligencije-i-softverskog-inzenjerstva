(ns project.core
  (:gen-class) )

;;NMM
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
      (* p0* sigmau)
      "Incorrect value of the Coefficient of recovery of the total pressure in the inlet")
    "Incorrect value of the atmospheric pressure"))

;;9.User needs temperature in the inlet
(defn T1*
  [T0 tau]
  (if (> T0 0)
    (if (> tau 0)
      (* T0 tau)
      "Incorrect value of the Coefficient of temperature")
    "Incorrect value of the ambient temperature"))

;;10.User needs Coefficient of pressure in the inlet
(defn piu-v1
  [tau kv]
  (if (> tau 0)
    (if (> kv 1)
      (Math/pow tau (fn1 kv))
      "Incorrect value of the adiabatic constant of air")
    "Incorrect value of the Coefficient inlet"))

(defn piu-v2
  [p1 p0]
   (if (> p1 0)
     (if (> p0 1)
       (/ p1 p0)
       "Incorrect value of the atmospheric pressure")
     "Incorrect value of the pressure from inlet"))

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
[vi c1 fi]
(if (> vi 0)
  (if (> c1 0)
    (if (> fi 0)
      (Math/pow (/ vi (* c1 fi)) 2)
      "Incorrect value of the Coefficient of discharge from the nozzle")
    "Incorrect value of the parameter")
  "Incorrect value of the Nozzle exit velocity"))

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
  [tau v fsp c1]
  (if (> tau 0)
    (if (> v 0)
      (if (> fsp 0)
        (if (> c1 0)
          (- tau (Math/pow (/ (v fsp) c1) 2))
          "Incorrect value of the constant")
        "Incorrect value of the Specific thrust")
      "Incorrect value of the movement speed")
    "Incorrect value of the Coefficient inlet"))

;;Combustion chamber
;;22.User needs combustion chamber pressure
(defn p2* 
[p1* sigmap]
(if (> p1* 0)
  (if (> sigmap 0)
    (* p1* sigmap)
    "Incorrect value of the Pressure drop due to heat supply")
  "Incorrect value of the pressure from inlet"))

;;23.User needs combustion chamber temperature
(defn T2*
[T0 theta]
(if (> T0 0)
  (if (> theta 0)
    (* T0 theta)
    "Incorrect value of the degree of heating")
  "Incorrect value of the ambient temperature"))

;;Nozzle
;;24.User needs Available expansion rate in the nozzle
(defn pimr
[p2* p0]
(if (> p2* 0)
  (if (> p0 0)
    (/ p2* p0)
    "Incorrect value of the atmospheric pressure")
  "Incorrect value of the combustion chamber pressure"))

 ;;25.User needs Convergent nozzle coefficient
 (defn conv-pim
 [pimr1 pikrit]
 (if (> pimr1 0)
  (if (> pikrit 0)
    (if (> pimr pikrit)
      (pikrit)
      (pimr1))
    "Incorrect value of the Critical degree of expansion in the nozzle")
  "Incorrect value of the Available expansion rate in the nozzle")) 

;;26.User needs Convergent-Divergent nozzle coefficient
 (defn conv-div-pim
 [pimr pikrit]
 (if (> pimr 0)
  (if (> pikrit 0)
    (if (> pimr pikrit)
      (pikrit)
      "Non-existent case")
    "Incorrect value of the Critical degree of expansion in the nozzle")
  "Incorrect value of the Available expansion rate in the nozzle")) 

;;27.User needs Nozzle exit velocity
(defn vi
[fsp v0 q]
(if (> fsp 0)
   (if (> v0 0)
     (if (> q 0)
       (/ (+ fsp v0) (+ 1 q))
       "Incorrect value of the Mixing ratio of fuel and air")
     "Incorrect value of the Movement speed")
   "Incorrect value of the Specific thrust"))

;;28.User needs Fuel mass flow
(defn mg
 [q mv]
 (if (> q 0)
  (if (> mv 0)
    (* q mv)
    "Incorrect value of the Mixing ratio of fuel and air")
  "Incorrect value of the  mass air flow")) 

;;29.User needs Specific fuel consumption with 2 different measurement units
(defn csps
[mg F]
 (if (> mg 0)
  (if (> F 0)
    (/ mg F)
    "Incorrect value of the thrust")
  "Incorrect value of the Fuel mass flow"))

(defn csph
  [mg F]
   (if (> mg 0)
    (if (> F 0)
     (* (csps mg F) 36000)
     "Incorrect value of the thrust")
  "Incorrect value of the Fuel mass flow"))

;;TMM
;;compressor
;;30.User needs temperature in the compressor
(defn t2-tmm
  [t1 pik kv nik]
  (if (> t1 0)
    (if (> pik 0)
      (if (> kv 1)
        (if (> nik 0)
          (* t1 (+ 1 (* (- (Math/pow pik (fn2 kv)) 1) (/ 1 nik))))
          "Incorrect value of the Efficiency coefficient of the compressor")
        "Incorrect value of the adiabatic constant of air")
      "Incorrect value of the input parameter")
    "Incorrect value of the temperature in the inlet"))

;;31.User needs compressor pressure
(defn p2-tmm
  [p1 pik]
  (if (> p1 0)
    (if (> pik 0)
      (* p1 pik)
      "Incorrect value of the input parameter")
    "Incorrect value of the inlet pressure"))

;;32.User needs tau parameter of the compressor
(defn tau-sum
  [tau1 tau2]
  (if (> tau1 0)
    (if (> tau2 0)
      (* tau1 tau2)
      "Incorrect value of the tau1 parameter")
    "Incorrect value of the tau2 parameter"))

;;33.User needs tau temperature parameter
(defn tau-k
  [t2 t1]
  (if (> t2 0)
    (if (> t1 0)
      (* t1 t2)
      "Incorrect value of temperature in the inlet")
    "Incorrect value of the temperature in the compressor"))

;;34.User needs pi parameter of the compressor
(defn pi-sum
  [pi1 pi2]
  (if (> pi1 0)
    (if (> pi2 0)
      (* pi1 pi2)
      "Incorrect value of the pi1 parameter")
    "Incorrect value of the pi2 parameter"))

;;TMM-Combustion chamber
;;35.User needs pressure in the combustion chamber
(defn p3-tmm
  [sigmap p2]
  (if (> sigmap 0)
    (if (> p2 0)
      (* sigmap p2)
      "Incorrect value of the compressor pressure")
    "Incorrect value of the Pressure drop due to heat supply"))

;;36.User needs tmm degree of heating
(defn tmm-degree-heating
  [t3 t0]
  (if (> t3 0)
    (if (> t0 0)
      (/ t3 t0)
      "Incorrect value of the ambient temperature")
    "Incorrect value of the Maximum temperature in the cycle"))

;;37.User needs tmm Mixing ratio of fuel and air
(defn tmm-mixing-ratio
  [cpps t3  cpv t2 sigmag hd]
  (if (> cpps 0)
    (if (> t3 0)
      (if (> cpv 0)
        (if (> t2 0)
          (if (> sigmag 0)
            (if (> hd 0)
              (/ (- (* cpps t3) (* cpv t2)) (- (* sigmag hd) (* cpps t3)))
              "Incorrect value of the Lower heating value of fuel")
            "Incorrect value of the Coefficient of completeness of combustion")
          "Incorrect value of the temperature in the compressor")
        "Incorrect value of the Specific heat capacity of air")
      "Incorrect value of the Maximum temperature in the cycle")
    "Incorrect value of the Specific heat capacity of combustion products"))

;;Turbine
;;38.User needs parameter pi of turbine
(defn pi-turbine
  [t4 t3 nit kps]
  (if (> t4 0)
    (if (> t3 0)
      (if (> nit 1)
        (if (> kps 0)
          (Math/pow (- 1 (* (- 1 (/ t4 t3)) (/ 1 nit))) (- 0 (fn1 kps)) )
          "Incorrect value of the Adiabatic constant of combustion products")
        "Incorrect value of the Turbine efficiency")
      "Incorrect value of the Maximum temperature in the cycle")
    "Incorrect value of the turbine temperature"))

;;39.User needs turbine pressure
(defn tmm-p4
  [p3 pit]
  (if (> p3 0)
    (if (> pit 0)
      (/ p3 pit)
      "Incorrect value of the pi parameter of turbine")
    "Incorrect value of the  pressure in the combustion chamber"))

;;40.User needs turbine temperature
(defn tmm-t4
  [t3 wt cpps]
  (if (> t3 0)
    (if (> wt 0)
      (if (> cpps 0)
        (- t3 (/ wt cpps))
        "Incorrect value of the Specific heat capacity of combustion products")
      "Incorrect value of the Turbine w parameter")
    "Incorrect value of the Maximum temperature in the cycle"))

;;41.User needs turbine tau parameter
(defn tau-t
  [t3 t4]
  (if (> t3 0)
    (if (> t4 0)
      (/ t3 t4)
      "Incorrect value of the turbine temperature")
    "Incorrect value of the Maximum temperature in the cycle"))

;;42.User needs compressor w parameter
(defn wk
  [cpv t2 t1]
  (if (> cpv 0)
    (if (> t2 0)
      (if (> t1 0)
        (* cpv (- t2 t1))
        "Incorrect value of the temperature in the inlet")
      "Incorrect value of the temperature in the compressor")
    "Incorrect value of the Specific heat capacity of air"))

;;43.User needs turbine w parameter
(defn wt
  [mv wk q sigma nim]
  (if (> mv 0)
    (if (> wk 0)
      (if (> q 0)
        (if (> sigma 0)
          (if (> nim 0)
            (/ (* mv wk) (* mv (* nim (+ 1 (- q sigma)))) )
            "Incorrect value of the Coefficient of mechanical usefulness")
          "Incorrect value of the sigma parameter")
        "Incorrect value of the Mixing ratio of fuel and air")
      "Incorrect value of the compressor w parameter")
    "Incorrect value of the Mass air flow"))