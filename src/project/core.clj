(ns project.core
  (:gen-class) )


;;multiplication ;;functions p1*,t1*,aircraft-weight-p2*,t2*,mg,csph||  p2-tmm tau-sum tau-k pi-sum p3-tmm tmm-csph
(defn multiplication
  [a b]
  (* a b))

;;division ;;functions pimr piu-v2,thrust,specific-thrust,csps||  tmm-degree-heating tmm-p4 tau-t tmm-pimr pout tmm-degree-heating
(defn division
  [a b]
  (/ a b))
;;0
(defn greater-than-one? [n]
  (> n 1))
;;NMM
 ;;1.The user wants to get a parameters that makes the calculation easier for him
(defn fn1
  "kv / (kv - 1)  or  kps/(kps-1)"
  [par1]
  (/ par1 (- par1 1)))

(defn fn2
  "(kv-1)/kv or (kps-1)/kps"
  [par]
  (/ (- par 1) par))

(defn fn3
  "1/(kps-1)"
  [par]
  (/ 1 (- par 1)))

(defn fn4
  "(kv-1)/2"
  [par]
  (/ (- par 1) 2))
;;2.The user wants to get the ambient temperature depending on the flight altitude
(defn T0
  [H]
  (if (> H 11000)
    (- 288.15 (* 11000 0.0065))
    (- 288.15 (* H 0.0065))))
;;3.The user wants to get the density of the environment depending on the flight altitude
(defn ro0
  [H]
  (if (<= H 11000)
    (* 1.225 (Math/pow (- 1 (* 0.0065 (/ H 288.15))) 4.2561))
    (* 0.364 (Math/exp (/ (- 11000 H) 6330)))))

;;4.The user wants to get the atmospheric pressure at a specific flight altitude
(defn p0
  [H]
  (if (> H 11000)
    (* (* 101325 (Math/pow (- 1 (* 0.0065 (/ 11000 288.15))) 5.2561)) (Math/exp (/ (- 11000 H) 6330)))
    (* 101325 (Math/pow (- 1 (* 0.0065 (/ H 288.15))) 5.2561))))

;;5.The user wants to get the speed of sound based on the parameters entered
(defn a0 
[kv Rv T0]
  (Math/sqrt (* kv (* Rv T0))))

;;Inlet
;;6.User needs Coefficient inlet
  (defn tauu [kv M0]
    (+ 1 (* (fn4 kv) (Math/pow M0 2))))

;;7.The user needs a pressure value in the inlet
(defn p0*
  [p0 kv M0]
  (* p0 (Math/pow (tauu kv M0) (fn1 kv))))

;;10.User needs Coefficient of pressure in the inlet
(defn piu-v1
  [tau kv]
  (Math/pow tau (fn1 kv)))

;;Additional parameters
;;14.User needs specific thrust
;;System of 3 equations
;;15.User needs degree of heating
(defn degree-of-heating
[vi c1 fi]
  (Math/pow (/ vi (* c1 fi)) 2))

;;16.User needs Mass air flow
(defn air-flow
  [v0 ro0 Au]
  (* v0 ro0 Au))

;;17.User needs Constant for easy calculation
(defn easy-calculation
  [Cpps T0 pimr kps]
  (Math/sqrt (* 2 (* Cpps (* T0 (- 1 (Math/pow (/ 1 pimr) (fn2 kps))))))))

;;18.User needs Mixing ratio of fuel and air
(defn ratio-fuel-air
  [a b c]
  (/ (- (Math/sqrt (- (Math/pow b 2) (* 4 (* a c)))) b) (* 2 a)))

;;19.User needs coefficient a
(defn coeff-a
[Hd sigmag Cpv T0]
  (/ (* Hd sigmag) (* Cpv T0)))

;;20.User needs coefficient b
(defn coeff-b
  [tau a]
  (+ tau a) )

;;21.User needs coefficient c
(defn coeff-c
  [tau cpps cpv v0 fsp c1 r]
  (- tau (* (/ cpps cpv) (/ (Math/pow (+ v0 fsp) 2) (* (Math/pow c1 2) (Math/pow r 2))))))

;;Combustion chamber
;;22.User needs combustion chamber pressure
;;jet
 ;;25.User needs Convergent jet coefficient
(defn conv-pim
  [pimr1 pikrit]
  (if (> pimr1 pikrit)
    pikrit
    pimr1))

;;26.User needs Jet exit velocity
(defn vi
[fsp v0 q]
  (/ (+ fsp v0) (+ 1 q)))
;;TMM
;;compressor
;;29.User needs temperature in the compressor
(defn t2-tmm
  [t1 pik kv nik]
  (* t1 (+ 1 (* (- (Math/pow pik (fn2 kv)) 1) (/ 1 nik)))))

;;30.User needs compressor pressure
;;35.User needs tmm degree of heating

;;36.User needs tmm Mixing ratio of fuel and air
(defn tmm-mixing-ratio
  [cpps t3  cpv t2 sigmag hd]
  (/ (- (* cpps t3) (* cpv t2)) (- (* sigmag hd) (* cpps t3))))

;;Turbine
;;37.User needs parameter pi of turbine
(defn pi-turbine
  [t4 t3 nit kps]
  (Math/pow (- 1 (* (- 1 (/ t4 t3)) (/ 1 nit))) (- 0 (fn1 kps)) ))

;;39.User needs turbine temperature
(defn tmm-t4
  [t3 wt cpps]
  (- t3 (/ wt cpps)))

;;40.User needs turbine tau parameter
;;41.User needs compressor w parameter
(defn wk
  [cpv t2 t1]
  (* cpv (- t2 t1)))

;;42.User needs turbine w parameter
(defn wt
  [mv wk q sigma nim]
  (/ (* mv wk) (* mv (* nim (+ 1 (- q sigma)))) ))

;;TMM-jet
;;43.User needs Available expansion rate in the TMM-jet

;;44.User needs mass parameter at the jet
(defn mps
  [mv q sigma]
  (* mv (+ 1 (- q sigma)) ) )

;;45.User needs output pressure

;;46.User needs output velocity
(defn tmm-vi
  [fi cpps t4 pim kps]
  (* fi (Math/sqrt (* 2 (* cpps (* t4 (- 1 (/ 1 (Math/pow pim (fn2 kps))))))))))

;;47.User needs parameter dependent on Mach number
(defn func-M
  [m kps]
  (* m (Math/pow (+ m (* (/ (- kps 1) 2) (Math/pow m 2))) (- (/ (+ kps 1) (* 2 (- kps 1)))))))

;;48.User needs output A
(defn out-a
  [mps fm rps kps t4 p4]
  (* (/ mps fm) (* (Math/sqrt (/ rps kps)) (/ (Math/sqrt t4) p4))))

;;49.User needs tmm thrust
(defn tmm-thrust
  [mv q sigma vi v0 aout pout p0]
  (+ (* mv (- (* (- (+ 1 q) sigma) vi) v0)) (* aout (- pout p0))))

;;50.User needs Specific fuel consumption with 2 different measurement units
(defn tmm-csps
[q mv F]
  (/ (* q mv) F))

;;51.Parse values from JTextFields
(defn parse [text]
  (try
    (Double/parseDouble text)
    (catch Exception _ -1)))

;;52.Validate fields
(defn validate-fields [greater-than-zero-fields greater-than-one-fields]
  (let [values-zero (map parse (map #(.getText %) greater-than-zero-fields))
        values-one  (map parse (map #(.getText %) greater-than-one-fields))
        all-greater-than-zero? (every? pos? values-zero)
        all-greater-than-one?  (every? greater-than-one? values-one)]
    {:valid (and all-greater-than-zero? all-greater-than-one?)
     :values-zero values-zero
     :values-one values-one}))