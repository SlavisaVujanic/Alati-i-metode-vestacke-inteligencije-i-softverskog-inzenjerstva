(ns project.gui
  (:import (java.util Locale)
           (javax.swing JOptionPane))
  (:require [project.core :as proj]))
(Locale/setDefault Locale/US)

(import '(javax.swing JFrame JLabel JTextField JButton)
        '(java.awt.event ActionListener)
        '(java.awt GridLayout))

(let [main-frame (new JFrame "Aircraft Performance")
      main-nmm-button (new JButton "NMM")
      main-tmm-button (new JButton "TMM")
      main-compare-button (new JButton "Compare")

      nmm-frame (new JFrame "NMM")
      par1-label (new JLabel "Cpv")
      par2-label (new JLabel "Cpps")
      par3-label (new JLabel "kv")
      par4-label (new JLabel "kps")
      par6-label (new JLabel "v0")
      par7-label (new JLabel "Au")
      par8-label (new JLabel "H")
      par9-label (new JLabel "a0")
      par10-label (new JLabel "M0")
      par11-label (new JLabel "\u03C3u")
      par12-label (new JLabel "\u03B7k")
      par13-label (new JLabel "σp")
      par14-label (new JLabel "σg")
      par15-label (new JLabel "ηt")
      par16-label (new JLabel "ηm")
      par17-label (new JLabel "\u03C6")
      par18-label (new JLabel "Hd")
      par19-label (new JLabel "Rv")
      par20-label (new JLabel "Rps")
      par21-label (new JLabel "\u03c0krit")
      par22-label (new JLabel "Cz/Cx")
      par23-label (new JLabel "m")
      par24-label (new JLabel "g")
      par25-label (new JLabel "T0")
      par26-label (new JLabel "ρ0")
      par27-label (new JLabel "p0")
      exp1-label (new JLabel "kv/(kv-1)")
      exp2-label (new JLabel "kps/(kps-1)")
      exp3-label (new JLabel "(kv-1)/kv")
      exp4-label (new JLabel "1/(kps-1)")
      exp5-label (new JLabel "(kps-1)/kps")
      exp6-label (new JLabel "(kv-1)/2")
      expression-label (new JLabel "Expressions")
      inlet-label (new JLabel "Inlet")
      inlet-label1 (new JLabel "p0*")
      inlet-label2 (new JLabel "p1(real)*")
      inlet-label3 (new JLabel "T1*")
      inlet-label4 (new JLabel "\u03C0u")
      inlet-label5 (new JLabel "\u03C4u")
      chamber-label (new JLabel "Combustion chamber")
      chamber-label1 (new JLabel "p2*")
      chamber-label3 (new JLabel "T2*")
      jet-label (new JLabel "Jet")
      jet-label1 (new JLabel "πmr")
      jet-label2 (new JLabel "πm")
      jet-label3 (new JLabel "Vi")
      q-label (new JLabel "Parameters of the quadratic equation")
      a-label (new JLabel "a")
      b-label (new JLabel "b")
      c-label (new JLabel "c")
      other-label (new JLabel "Other parameters")
      g-label (new JLabel "G")
      f-label (new JLabel "F")
      fsp-label (new JLabel "Fsp")
      q-label2 (new JLabel "q")
      theta-label4 (new JLabel "\u03B8")
      mg-label (new JLabel "mg")
      csp-label (new JLabel "Csp")
      mv-label (new JLabel "mv")
      c1-label (new JLabel "C1")
      par1-text (new JTextField)
      par2-text (new JTextField)
      par3-text (new JTextField)
      par4-text (new JTextField)
      par6-text (new JTextField)
      par7-text (new JTextField)
      par8-text (new JTextField)
      par9-text (new JTextField)
      par10-text (new JTextField)
      par11-text (new JTextField)
      par12-text (new JTextField)
      par13-text (new JTextField)
      par14-text (new JTextField)
      par15-text (new JTextField)
      par16-text (new JTextField)
      par17-text (new JTextField)
      par18-text (new JTextField)
      par19-text (new JTextField)
      par20-text (new JTextField)
      par21-text (new JTextField)
      par22-text (new JTextField)
      par23-text (new JTextField)
      par24-text (new JTextField)
      par25-text (new JTextField)
      par26-text (new JTextField)
      par27-text (new JTextField)
      exp1-text (new JTextField)
      exp2-text (new JTextField)
      exp3-text (new JTextField)
      exp4-text (new JTextField)
      exp5-text (new JTextField)
      exp6-text (new JTextField)
      inlet-text1 (new JTextField)
      inlet-text2 (new JTextField)
      inlet-text3 (new JTextField)
      inlet-text4 (new JTextField)
      inlet-text5 (new JTextField)
      chamber-text1 (new JTextField)
      q-text2 (new JTextField)
      chamber-text3 (new JTextField)
      theta-text (new JTextField)
      jet-text1 (new JTextField)
      jet-text2 (new JTextField)
      jet-text3 (new JTextField)
      a-text (new JTextField)
      b-text (new JTextField)
      c-text (new JTextField)
      g-text (new JTextField)
      f-text (new JTextField)
      fsp-text (new JTextField)
      mg-text (new JTextField)
      csp-text1 (new JTextField)
      csp-text2 (new JTextField)
      mv-text (new JTextField)
      c1-text (new JTextField)
      calc-nmm-button (new JButton "Calculate")
      check-btn (new JButton "Check")
      reset-btn (new JButton "Reset")
      back-btn (new JButton "Back")
      tmm-frame (new JFrame "TMM")
      compare-frame (new JFrame "Compare")
      fields-one [par3-text par4-text]
      fields-zero [par1-text par2-text  par6-text par7-text par8-text par9-text par10-text par11-text par12-text par13-text par14-text
                   par15-text par16-text par17-text par18-text par19-text par20-text  par21-text  par22-text  par23-text  par24-text]
      fields-expr [par25-text par26-text par27-text exp1-text exp2-text exp3-text exp4-text exp5-text exp6-text inlet-text1 inlet-text2 inlet-text3 inlet-text4 inlet-text5
                   chamber-text1 chamber-text3 q-text2 jet-text1 jet-text2 jet-text3 a-text b-text c-text g-text f-text fsp-text mg-text csp-text1 csp-text2 mv-text c1-text theta-text]
      labels-one [par3-label par4-label]
      labels-zero [par1-label par2-label  par6-label par7-label par8-label par9-label par10-label par11-label par12-label par13-label par14-label
                   par15-label par16-label par17-label par18-label par19-label par20-label  par21-label  par22-label  par23-label  par24-label]
      labels-expr [ other-label q-label jet-label chamber-label inlet-label  expression-label par25-label par26-label par27-label exp1-label exp2-label exp3-label exp4-label exp5-label exp6-label inlet-label1 inlet-label2 inlet-label3 inlet-label4 inlet-label5
                   chamber-label1 chamber-label3 q-label2 jet-label1 jet-label2 jet-label3 a-label b-label c-label g-label f-label fsp-label mg-label csp-label mv-label c1-label theta-label4]
      btns [back-btn check-btn calc-nmm-button reset-btn]]



;;main-frame
  (.setLayout main-frame nil)
  (.setBounds main-nmm-button 0 0 100 50)
  (.setBounds main-compare-button 110 0 100 50)
  (.setBounds main-tmm-button 220 0 100 50)

  (.addActionListener main-nmm-button
                      (proxy [ActionListener] []
                        (actionPerformed [evt]
                          (.setVisible main-frame false)
                          (.setVisible nmm-frame true))))

  (.addActionListener main-tmm-button
                      (proxy [ActionListener] []
                        (actionPerformed [evt]
                          (.setVisible main-frame false)
                          (.setVisible tmm-frame true))))

  (.addActionListener main-compare-button
                      (proxy [ActionListener] []
                        (actionPerformed [evt]
                          (.setVisible main-frame false)
                          (.setVisible compare-frame true))))

  ;;nmm-frame
  (.setLayout nmm-frame nil)
  (.setBounds par1-label 0 5 40 30)
  (.setBounds par1-text 50 5 50 30)
  (.setBounds par2-label 110 5 40 30)
  (.setBounds par2-text 160 5 50 30)
  (.setBounds par3-label 220 5 40 30)
  (.setBounds par3-text 270 5 50 30)
  (.setBounds par4-label 330 5 40 30)
  (.setBounds par4-text 380 5 50 30)
  (.setBounds par24-label 0 45 40 30)
  (.setBounds par24-text 50 45 50 30)
  (.setBounds par6-label 110 45 40 30)
  (.setBounds par6-text 160 45 50 30)
  (.setBounds par7-label 220 45 40 30)
  (.setBounds par7-text 270 45 50 30)
  (.setBounds par8-label 330 45 40 30)
  (.setBounds par8-text 380 45 50 30)
  (.setBounds par9-label 0 85 40 30)
  (.setBounds par9-text 50 85 50 30)
  (.setBounds par10-label 110 85 40 30)
  (.setBounds par10-text 160 85 50 30)
  (.setBounds par11-label 220 85 40 30)
  (.setBounds par11-text 270 85 50 30)
  (.setBounds par12-label 330 85 40 30)
  (.setBounds par12-text 380 85 50 30)
  (.setBounds par13-label 0 125 40 30)
  (.setBounds par13-text 50 125 50 30)
  (.setBounds par14-label 110 125 40 30)
  (.setBounds par14-text 160 125 50 30)
  (.setBounds par15-label 220 125 40 30)
  (.setBounds par15-text 270 125 50 30)
  (.setBounds par16-label 330 125 40 30)
  (.setBounds par16-text 380 125 50 30)
  (.setBounds par17-label 0 165 40 30)
  (.setBounds par17-text 50 165 50 30)
  (.setBounds par18-label 110 165 40 30)
  (.setBounds par18-text 160 165 50 30)
  (.setBounds par19-label 220 165 40 30)
  (.setBounds par19-text 270 165 50 30)
  (.setBounds par20-label 330 165 40 30)
  (.setBounds par20-text 380 165 50 30)
  (.setBounds par21-label 0 205 40 30)
  (.setBounds par21-text 50 205 50 30)
  (.setBounds par22-label 110 205 40 30)
  (.setBounds par22-text 160 205 50 30)
  (.setBounds par23-label 220 205 40 30)
  (.setBounds par23-text 270 205 50 30)
  (.setBounds par25-label 0 245 40 30)
  (.setBounds par25-text 50 245 50 30)
  (.setEditable par25-text false)
  (.setBounds par26-label 110 245 40 30)
  (.setBounds par26-text 160 245 50 30)
  (.setEditable par26-text false)
  (.setBounds par27-label 220 245 40 30)
  (.setBounds par27-text 270 245 60 30)
  (.setEditable par27-text false)
  (.setBounds check-btn 50 280 100 20)
  (.setBounds calc-nmm-button 160 280 100 20)
  (.setEnabled calc-nmm-button false)
  (.setBounds reset-btn 270 280 100 20)
  (.setEnabled reset-btn false)
  (.setBounds expression-label 5 300 80 20)
  (.setBounds exp1-label 5 325 60 30)
  (.setBounds exp1-text 70 325 50 30)
  (.setEditable exp1-text false)
  (.setBounds exp2-label 130 325 70 30)
  (.setBounds exp2-text 210 325 50 30)
  (.setEditable exp2-text false)
  (.setBounds exp3-label 280 325 60 30)
  (.setBounds exp3-text 350 325 50 30)
  (.setEditable exp3-text false)
  (.setBounds exp4-label 5 365 60 30)
  (.setBounds exp4-text 70 365 50 30)
  (.setEditable exp4-text false)
  (.setBounds exp5-label 130 365 70 30)
  (.setBounds exp5-text 210 365 50 30)
  (.setEditable exp5-text false)
  (.setBounds exp6-label 280 365 60 30)
  (.setBounds exp6-text 350 365 50 30)
  (.setEditable exp6-text false)
  (.setBounds inlet-label 5 395 60 20)
  (.setBounds inlet-label1 5 420 40 30)
  (.setBounds inlet-text1 55 420 70 30)
  (.setEditable inlet-text1 false)
  (.setBounds inlet-label2 130 420 50 30)
  (.setBounds inlet-text2 195 420 70 30)
  (.setEditable inlet-text2 false)
  (.setBounds inlet-label3 280 420 60 30)
  (.setBounds inlet-text3 350 420 50 30)
  (.setEditable inlet-text3 false)
  (.setBounds inlet-label4 5 460 60 30)
  (.setBounds inlet-text4 70 460 50 30)
  (.setEditable inlet-text4 false)
  (.setBounds inlet-label5 130 460 70 30)
  (.setBounds inlet-text5 210 460 50 30)
  (.setEditable inlet-text5 false)
  (.setBounds chamber-label 5 490 120 15)
  (.setBounds chamber-label1 5 510 20 20)
  (.setBounds chamber-text1 55 510 70 30)
  (.setEditable chamber-text1 false)
  (.setBounds chamber-label3 130 510 20 20)
  (.setBounds chamber-text3 210 510 50 30)
  (.setEditable chamber-text3 false)
  (.setBounds jet-label 5 545 40 20)
  (.setBounds jet-label1 5 570 30 20)
  (.setBounds jet-text1 70 570 50 30)
  (.setEditable jet-text1 false)
  (.setBounds jet-label2 130 570 25 20)
  (.setBounds jet-text2 210 570 50 30)
  (.setEditable jet-text2 false)
  (.setBounds jet-label3 280 570 25 20)
  (.setBounds jet-text3 350 570 50 30)
  (.setEditable jet-text3 false)
  (.setBounds other-label 5 600 130 25)
  (.setBounds g-label 5 630 20 20)
  (.setBounds g-text 30 630 70 30)
  (.setEditable g-text false)
  (.setBounds f-label 130 630 20 20)
  (.setBounds f-text 160 630 70 30)
  (.setEditable f-text false)
  (.setBounds fsp-label 260 630 35 20)
  (.setBounds fsp-text 330 630 70 30)
  (.setEditable fsp-text false)
  (.setBounds q-label 5 670 230 25)
  (.setBounds a-label 5 700 20 20)
  (.setBounds a-text 30 700 60 30)
  (.setEditable a-text false)
  (.setBounds b-label 100 700 20 20)
  (.setBounds b-text 130 700 60 30)
  (.setEditable b-text false)
  (.setBounds c-label 200 700 20 20)
  (.setBounds c-text 230 700 60 30)
  (.setEditable c-text false)
  (.setBounds q-label2 300 700 20 20)
  (.setBounds q-text2 330 700 60 30)
  (.setEditable q-text2 false)
  (.setBounds mg-label 5 740 20 20)
  (.setBounds mg-text 30 740 60 30)
  (.setEditable mg-text false)
  (.setBounds csp-label 100 740 40 20)
  (.setBounds csp-text1 150 740 60 30)
  (.setBounds csp-text2 220 740 60 30)
  (.setEditable csp-text1 false)
  (.setEditable csp-text2 false)
  (.setBounds theta-label4 290 740 20 20)
  (.setBounds theta-text 320 740 50 30)
  (.setEditable theta-text false)
  (.setBounds mv-label 5 780 30 20)
  (.setBounds mv-text 40 780 60 30)
  (.setEditable mv-text false)
  (.setBounds c1-label 110 780 40 20)
  (.setBounds c1-text 160 780 60 30)
  (.setEditable c1-text false)
  (.setBounds back-btn 370 775 70 40)


  (.addActionListener back-btn
                      (proxy [ActionListener] []
                        (actionPerformed [evt]
                          (.setVisible nmm-frame false)
                          (.setVisible main-frame true)
                          (doseq [field (concat fields-zero fields-one fields-expr)]
                            (.setText field "")
                            (when (or (some #(= field %) fields-zero)
                                      (some #(= field %) fields-one))
                              (.setEditable field true)))
                          (.setEnabled check-btn true)
                          (.setEnabled reset-btn false)
                          (.setEnabled calc-nmm-button false))))

  (.addActionListener check-btn
                      (proxy [ActionListener] []
                        (actionPerformed [evt]
                          (let [validation-result (proj/validate-fields fields-zero fields-one)]
                            (if (:valid validation-result)
                              (do
                                (.setEnabled calc-nmm-button true)
                                (.setEnabled check-btn false)
                                (doseq [field (concat fields-zero fields-one)]
                                  (.setEditable field false)))
                              (do
                                (cond
                                  (some #(<= % 0) (:values-zero validation-result))
                                  (JOptionPane/showMessageDialog nil "All values (except kv, kps) must be greater than 0.")
                                  (some #(not (proj/greater-than-one? % )) (:values-one validation-result))
                                  (JOptionPane/showMessageDialog nil "Some values (kv,kps) must be greater than 1."))
                                ))))))

(.addActionListener reset-btn
                    (proxy [ActionListener] []
                      (actionPerformed [evt]
                        (doseq [field (concat fields-zero fields-one fields-expr)]
                          (.setText field "")
                          (when (or (some #(= field %) fields-zero)
                                    (some #(= field %) fields-one))
                            (.setEditable field true)))

                        (.setEnabled calc-nmm-button false)
                        (.setEnabled check-btn true)
                        (.setEnabled reset-btn false))))

(.addActionListener calc-nmm-button
                    (proxy [ActionListener] []
                      (actionPerformed [evt]
                        (.setEnabled reset-btn true)
                        (.setEnabled check-btn false)
                        (.setEnabled calc-nmm-button false)
                        (.setText par25-text (format "%.3f" (proj/T0 (Double/parseDouble (.getText par8-text)))))
                        (.setText par26-text (format "%.3f" (proj/ro0 (Double/parseDouble (.getText par8-text)))))
                        (.setText par27-text (format "%.3f" (proj/p0 (Double/parseDouble (.getText par8-text)))))
                        (.setText exp1-text (format "%.3f" (proj/fn1 (Double/parseDouble (.getText par3-text)))))
                        (.setText exp2-text (format "%.3f" (proj/fn1 (Double/parseDouble (.getText par4-text)))))
                        (.setText exp3-text (format "%.3f" (proj/fn2 (Double/parseDouble (.getText par3-text)))))
                        (.setText exp4-text (format "%.3f" (proj/fn3 (Double/parseDouble (.getText par4-text)))))
                        (.setText exp5-text (format "%.3f" (proj/fn2 (Double/parseDouble (.getText par4-text)))))
                        (.setText exp6-text (format "%.3f" (proj/fn4 (Double/parseDouble (.getText par3-text)))))
                        (.setText inlet-text1 (format "%.3f" (proj/p0* (Double/parseDouble(.getText par27-text)) (Double/parseDouble (.getText par3-text)) (Double/parseDouble (.getText par10-text)))))
                        (.setText inlet-text2 (format "%.3f" (proj/multiplication (Double/parseDouble(.getText inlet-text1)) (Double/parseDouble (.getText par11-text)))))
                        (.setText inlet-text4 (format "%.3f" (proj/division (Double/parseDouble(.getText inlet-text2)) (Double/parseDouble (.getText par27-text)))))
                        (.setText inlet-text5 (format "%.3f" (proj/tauu (Double/parseDouble(.getText par3-text)) (Double/parseDouble (.getText par10-text)))))
                        (.setText inlet-text3 (format "%.3f" (proj/multiplication (Double/parseDouble(.getText inlet-text5)) (Double/parseDouble (.getText par25-text)))))
                        (.setText chamber-text1 (format "%.3f" (proj/multiplication (Double/parseDouble(.getText par13-text)) (Double/parseDouble (.getText inlet-text2)))))
                        (.setText jet-text1 (format "%.3f" (proj/division (Double/parseDouble(.getText chamber-text1)) (Double/parseDouble (.getText par27-text)))))
                        (.setText jet-text2 (format "%.3f" (proj/conv-pim (Double/parseDouble(.getText jet-text1)) (Double/parseDouble (.getText par21-text)))))
                        (.setText g-text (format "%.3f" (proj/multiplication (Double/parseDouble(.getText par23-text)) (Double/parseDouble (.getText par24-text)))))
                        (.setText f-text (format "%.3f" (proj/division (Double/parseDouble(.getText g-text)) (Double/parseDouble (.getText par22-text)))))
                        (.setText mv-text (format "%.3f" (proj/air-flow (Double/parseDouble(.getText par6-text)) (Double/parseDouble (.getText par26-text)) (Double/parseDouble (.getText par7-text)))))
                        (.setText c1-text (format "%.3f" (proj/easy-calculation (Double/parseDouble(.getText par2-text)) (Double/parseDouble (.getText par25-text)) (Double/parseDouble (.getText jet-text1)) (Double/parseDouble (.getText par4-text)))))
                        (.setText a-text (format "%.3f" (proj/coeff-a (Double/parseDouble(.getText par14-text)) (Double/parseDouble(.getText par18-text))(Double/parseDouble (.getText par1-text)) (Double/parseDouble (.getText par25-text)))))
                        (.setText b-text (format "%.3f" (proj/coeff-b (Double/parseDouble(.getText inlet-text5)) (Double/parseDouble (.getText a-text)))))
                        (.setText fsp-text (format "%.3f" (proj/division (Double/parseDouble(.getText f-text)) (Double/parseDouble (.getText mv-text)))))
                        (.setText c-text (format "%.3f" (proj/coeff-c (Double/parseDouble(.getText inlet-text5)) (Double/parseDouble (.getText par2-text)) (Double/parseDouble(.getText par1-text)) (Double/parseDouble(.getText par6-text)) (Double/parseDouble(.getText fsp-text)) (Double/parseDouble(.getText c1-text))(Double/parseDouble(.getText par17-text)))))
                        (.setText q-text2 (format "%.3f" (proj/ratio-fuel-air (Double/parseDouble(.getText a-text)) (Double/parseDouble (.getText b-text)) (Double/parseDouble (.getText c-text)))))
                        (.setText jet-text3 (format "%.3f" (proj/vi (Double/parseDouble(.getText fsp-text)) (Double/parseDouble (.getText par6-text)) (Double/parseDouble (.getText q-text2)))))
                        )))


  (doto main-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 330 87)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible true)
    (.add main-nmm-button)
    (.add main-compare-button)
    (.add main-tmm-button))

  (doseq [component (concat fields-one fields-zero fields-expr labels-expr labels-one labels-zero btns)]
    (.add nmm-frame component))

  (doto nmm-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 450 850)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible false))

  (doto tmm-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 330 87)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible false))

  (doto compare-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 330 87)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible false)))