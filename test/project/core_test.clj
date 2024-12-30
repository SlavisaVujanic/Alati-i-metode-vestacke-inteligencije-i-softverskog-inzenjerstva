(ns project.core-test
  (:require [midje.sweet :refer :all]
            [project.core :refer :all])
  (:import (java.util Locale)))
(Locale/setDefault Locale/US)

(fact
  (multiplication 2 3) => 6)

(fact
  (division 4 2) => 2)

(fact
  (Double/parseDouble (format "%.3f" (fn1 1.4))) => (Double/parseDouble "3.500"))

(fact
  (Double/parseDouble (format "%.3f" (fn2 1.4))) => (Double/parseDouble "0.286"))

(fact
  (Double/parseDouble (format "%.3f" (fn3 1.33))) => (Double/parseDouble "3.030"))

(fact
  (Double/parseDouble (format "%.3f" (fn4 1.4))) => (Double/parseDouble "0.200"))

(fact
  (Double/parseDouble (format "%.3f" (T0 20000))) => (Double/parseDouble "216.650"))

(fact
  (Double/parseDouble (format "%.3f" (ro0 20000))) => (Double/parseDouble "0.088"))

(fact
  (Double/parseDouble (format "%.3f" (p0 20000))) => (Double/parseDouble "5460.295"))

(fact
  (Double/parseDouble (format "%.3f" (a0 1.4 287 216.65))) => (Double/parseDouble "295.042"))

(fact
  (Double/parseDouble (format "%.3f" (tauu 1.4 3.25))) => (Double/parseDouble "3.112"))

(fact
  (Double/parseDouble (format "%.3f" (p0* 5460.295 1.4 3.25))) => (Double/parseDouble "290468.383"))

(fact
  (Double/parseDouble (format "%.3f" (easy-calculation 1150 216.65 50.047 1.33))) => (Double/parseDouble "556.387"))

(fact
  (Double/parseDouble (format "%.3f" (degree-of-heating 1618.197 556.388 0.95))) => (Double/parseDouble "9.373"))

(fact
  (Double/parseDouble (format "%.3f" (ratio-fuel-air 19.551 22.663 -1.052))) => (Double/parseDouble "0.045"))

(fact
  (Double/parseDouble (format "%.3f" (coeff-a 4300000 0.99 1005 216.65))) => (Double/parseDouble "19.551"))

(fact
  (Double/parseDouble (format "%.3f" (coeff-b 3.112 19.551))) => (Double/parseDouble "22.663"))

(fact
  (Double/parseDouble (format "%.3f" (coeff-c 3.112 1150 1005 958.887 49.383 556.387 0.95))) => (Double/parseDouble "-1.052"))

(fact
  (conv-pim 1.3 1.805)=> 1.3)

(fact
  (conv-pim 1.805 1.3)=> 1.3)

(fact
  (Double/parseDouble (format "%.3f" (vi 49.383 958.887 0.045 ))) => (Double/parseDouble "964.852"))

(fact
  (Double/parseDouble (format "%.3f" (t2-tmm 255.284 24.073 1.4 1))) => (Double/parseDouble "633.500"))

(fact
  (Double/parseDouble (format "%.3f" (tmm-mixing-ratio 1150 1800 1005 633.5 1 4300000))) => (Double/parseDouble "0.643"))

(fact
  (Double/parseDouble (format "%.3f" (pi-turbine 1597.595 1800 1 1.33))) => (Double/parseDouble "1.617"))