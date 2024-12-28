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