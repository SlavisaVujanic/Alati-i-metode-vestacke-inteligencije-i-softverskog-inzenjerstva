(ns project.core
  (:gen-class)
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

