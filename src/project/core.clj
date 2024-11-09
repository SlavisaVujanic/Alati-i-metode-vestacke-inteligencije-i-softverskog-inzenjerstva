(ns project.core
  (:gen-class) )
;;NMM ideal
;;B23  B24
(defn fn1
  "kv / (kv - 1)  or  kps/(kps-1)"
  [par1]
  (if (= par1 1)
    "Divide by 0!!!"
    (if (< par1 1)
      "Not allowed value"
      (/ par1 (- par1 1)))))
;;B25 B27
(defn fn2
  "(kv-1)/kv or (kps-1)/kps"
  [par]
  (if (> par 1)
    (/ (- par 1) par)
    "Not allowed value"))
;;B26
(defn fn3
  "1/(kps-1)"
  [par]
  (if (> par 1)
    (/ 1 (- par 1))
    "Not allowed value"))
;;B32
(defn fn4
  "(kv-1)/2"
  [par]
  (if (> par 1)
    (/ (- par 1) 2)
    "Not allowed value"))
;;B30
(defn sgps
  ""
  [kps Rg]
  (if (> kps 1)
    (if (> Rg 0)
      (* (Math/sqrt (* (/ (* 2 kps) (+ kps 1)) (/ 1 Rg)))
         (Math/pow (/ 2 (+ kps 1)) (fn3 kps)))
      "Invalid Rg value")
    "Invalid kps value"))
;;NMM real
;; 0-1* editorial real
;;E2
(defn p0*
  [kv M0 p0]
  (if (> kv 1)
    (if (>= M0 0)
      (if (> p0 0)
        (* p0 (Math/pow (+ 1 (* (/ (- kv 1) 2) (Math/pow M0 2))) (/ kv (- kv 1))))
        "p0 must be greater than 0")
      "M0 must be greater than or equal to 0")
    "kv must be greater than 0")
  )
;;E3 real
(defn p1*
  [M0 kv p0 sigmau]
    (if (>= M0 0)
      (if (> kv 1)
        (if (> p0 0)
          (if (> sigmau 0)
            (* (* sigmau p0) (Math/pow (+ 1 (* (fn4 kv) (Math/pow M0 2))) (fn1 kv)))
            "SigmaU must be greater than 0")
          "p0 must be greater than 0")
        "kv must be greater than 0")
      "M0 must be greater than or equal to 0")
  )
;;E5 
(defn piu
  [M0 kv p0 sigmau]
  (if (>= M0 0)
    (if (> kv 1)
      (if (> p0 0)
        (if (> sigmau 0)
          (/ (p1* M0 kv p0 sigmau) p0)
          "SigmaU must be greater than 0")
        "p0 must be greater than 0")
      "kv must be greater than 0")
    "M0 must be greater than or equal to 0")
 )

;;E6
(defn tauu
  [M0 kv p0 sigmau]
  (if (>= M0 0)
    (if (> kv 1)
      (if (> p0 0)
        (if (> sigmau 0)
          (Math/pow (piu M0 kv p0 sigmau) (fn2 kv))
          "SigmaU must be greater than 0")
        "p0 must be greater than 0")
      "kv must be greater than 0")
    "M0 must be greater than or equal to 0"))

;;E5 T1 real
(defn T1*
  [M0 kv p0 sigmau T0]
  (if (>= M0 0)
    (if (> kv 1)
      (if (> p0 0)
        (if (> sigmau 0)
          (if (> T0 0)
            (* (tauu M0 kv p0 sigmau) T0)
            "T0 must be greater than 0")
          "SigmaU must be greater than 0")
        "p0 must be greater than 0")
      "kv must be greater than 0")
    "M0 must be greater than or equal to 0"))

;;E8
(defn h0
  [cpv T0]
  (if (> cpv 0)
    (if (> T0 0)
      (* cpv T0)
      "T0 must be greater than 0")
    "cpv must be greater than 0")
  )

