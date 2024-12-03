(ns project.gui)
(import '(javax.swing JFrame JLabel JTextField JButton)
        '(java.awt.event ActionListener)
        '(java.awt GridLayout))

(let [main-frame (new JFrame "Aircraft Performance")
      main-nmm-button (new JButton "NMM")
      main-tmm-button (new JButton "TMM")
      main-compare-button (new JButton "Compare")

      nmm-frame (new JFrame "NMM")
      par1-label (new JLabel "Par1")
      par2-label (new JLabel "Par2")
      par3-label (new JLabel "Par3")
      par4-label (new JLabel "Par4")
      par5-label (new JLabel "Par5")
      par6-label (new JLabel "Par6")
      par7-label (new JLabel "Par7")
      par8-label (new JLabel "Par8")
      par9-label (new JLabel "Par9")
      par10-label (new JLabel "Par10")
      par11-label (new JLabel "Par11")
      par12-label (new JLabel "Par12")
      par13-label (new JLabel "Par13")
      par14-label (new JLabel "Par14")
      par15-label (new JLabel "Par15")
      par16-label (new JLabel "Par16")
      par17-label (new JLabel "Par17")
      par18-label (new JLabel "Par18")
      par19-label (new JLabel "Par19")
      par20-label (new JLabel "Par20")
      par21-label (new JLabel "Par21")
      par22-label (new JLabel "Par22")
      par23-label (new JLabel "Par23")
      par24-label (new JLabel "Par24")
      par25-label (new JLabel "Par25")
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
      chamber-label2 (new JLabel "q")
      chamber-label3 (new JLabel "T2*")
      chamber-label4 (new JLabel "\u03B8")
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
      par1-text (new JTextField)
      par2-text (new JTextField)
      par3-text (new JTextField)
      par4-text (new JTextField)
      par5-text (new JTextField)
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
      chamber-text2 (new JTextField)
      chamber-text3 (new JTextField)
      chamber-text4 (new JTextField)
      jet-text1 (new JTextField)
      jet-text2 (new JTextField)
      jet-text3 (new JTextField)
      a-text (new JTextField)
      b-text (new JTextField)
      c-text (new JTextField)
      g-text (new JTextField)
      f-text (new JTextField)
      fsp-text (new JTextField)
      calc-nmm-button (new JButton "Calculate")
      tmm-frame (new JFrame "TMM")
      compare-frame (new JFrame "Compare")]
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
  (.setBounds par5-label 0 45 40 30)
  (.setBounds par5-text 50 45 50 30)
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
  (.setBounds par24-label 330 205 40 30)
  (.setBounds par24-text 380 205 50 30)
  (.setBounds par25-label 0 245 40 30)
  (.setBounds par25-text 50 245 50 30)
  (.setBounds calc-nmm-button 160 280 100 20)
  (.setBounds expression-label 0 300 80 20)
  (.setBounds exp1-label 5 325 60 30)
  (.setBounds exp1-text 70 325 50 30)
  (.setBounds exp2-label 130 325 70 30)
  (.setBounds exp2-text 210 325 50 30)
  (.setBounds exp3-label 280 325 60 30)
  (.setBounds exp3-text 350 325 50 30)
  (.setBounds exp4-label 5 365 60 30)
  (.setBounds exp4-text 70 365 50 30)
  (.setBounds exp5-label 130 365 70 30)
  (.setBounds exp5-text 210 365 50 30)
  (.setBounds exp6-label 280 365 60 30)
  (.setBounds exp6-text 350 365 50 30)
  (.setBounds inlet-label 0 395 60 20)
  (.setBounds inlet-label1 5 420 60 30)
  (.setBounds inlet-text1 70 420 50 30)
  (.setBounds inlet-label2 130 420 70 30)
  (.setBounds inlet-text2 210 420 50 30)
  (.setBounds inlet-label3 280 420 60 30)
  (.setBounds inlet-text3 350 420 50 30)
  (.setBounds inlet-label4 5 460 60 30)
  (.setBounds inlet-text4 70 460 50 30)
  (.setBounds inlet-label5 130 460 70 30)
  (.setBounds inlet-text5 210 460 50 30)
  (.setBounds chamber-label 0 490 120 15)
  (.setBounds chamber-label1 0 510 20 20)
  (.setBounds chamber-text1 30 510 50 30)
  (.setBounds chamber-label2 90 510 20 20)
  (.setBounds chamber-text2 120 510 50 30)
  (.setBounds chamber-label3 180 510 20 20)
  (.setBounds chamber-text3 210 510 50 30)
  (.setBounds chamber-label4 270 510 20 20)
  (.setBounds chamber-text4 300 510 50 30)
  (.setBounds jet-label 0 545 40 20)
  (.setBounds jet-label1 0 570 30 20)
  (.setBounds jet-text1 45 570 50 30)
  (.setBounds jet-label2 100 570 25 20)
  (.setBounds jet-text2 130 570 50 30)
  (.setBounds jet-label3 185 570 25 20)
  (.setBounds jet-text3 215 570 50 30)
  (.setBounds other-label 0 600 130 25)
  (.setBounds g-label 0 630 20 20)
  (.setBounds g-text 30 630 70 30)
  (.setBounds f-label 110 630 20 20)
  (.setBounds f-text 140 630 70 30)
  (.setBounds fsp-label 220 630 35 20)
  (.setBounds fsp-text 265 630 70 30)
  (.setBounds q-label 0 670 230 25)
  (.setBounds a-label 0 700 20 20)
  (.setBounds a-text 30 700 70 30)
  (.setBounds b-label 110 700 20 20)
  (.setBounds b-text 140 700 70 30)
  (.setBounds c-label 220 700 20 20)
  (.setBounds c-text 250 700 70 30)



  (doto main-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 330 87)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible true)
    (.add main-nmm-button)
    (.add main-compare-button)
    (.add main-tmm-button))

  (doto nmm-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 450 850)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible false)
    (.add par1-label)
    (.add par1-text)
    (.add par2-label)
    (.add par2-text)
    (.add par3-label)
    (.add par3-text)
    (.add par4-label)
    (.add par4-text)
    (.add par5-label)
    (.add par5-text)
    (.add par6-label)
    (.add par6-text)
    (.add par7-label)
    (.add par7-text)
    (.add par8-label)
    (.add par8-text)
    (.add par9-label)
    (.add par9-text)
    (.add par10-label)
    (.add par10-text)
    (.add par11-label)
    (.add par11-text)
    (.add par12-label)
    (.add par12-text)
    (.add par13-label)
    (.add par13-text)
    (.add par14-label)
    (.add par14-text)
    (.add par15-label)
    (.add par15-text)
    (.add par16-label)
    (.add par16-text)
    (.add par17-label)
    (.add par17-text)
    (.add par18-label)
    (.add par18-text)
    (.add par19-label)
    (.add par19-text)
    (.add par20-label)
    (.add par20-text)
    (.add par21-label)
    (.add par21-text)
    (.add par22-label)
    (.add par22-text)
    (.add par23-label)
    (.add par23-text)
    (.add par24-label)
    (.add par24-text)
    (.add par25-label)
    (.add par25-text)
    (.add calc-nmm-button)
    (.add expression-label)
    (.add exp1-label)
    (.add exp1-text)
    (.add exp2-label)
    (.add exp2-text)
    (.add exp3-label)
    (.add exp3-text)
    (.add exp4-label)
    (.add exp4-text)
    (.add exp5-label)
    (.add exp5-text)
    (.add exp6-label)
    (.add exp6-text)
    (.add inlet-label)
    (.add inlet-label1)
    (.add inlet-text1)
    (.add inlet-label2)
    (.add inlet-text2)
    (.add inlet-label3)
    (.add inlet-text3)
    (.add inlet-label4)
    (.add inlet-text4)
    (.add inlet-label5)
    (.add inlet-text5)
    (.add chamber-label)
    (.add chamber-label1)
    (.add chamber-text1)
    (.add chamber-label2)
    (.add chamber-text2)
    (.add chamber-label3)
    (.add chamber-text3)
    (.add chamber-label4)
    (.add chamber-text4)
    (.add jet-label)
    (.add jet-label1)
    (.add jet-label2)
    (.add jet-label3)
    (.add jet-text1)
    (.add jet-text2)
    (.add jet-text3)
    (.add other-label)
    (.add g-label)
    (.add g-text)
    (.add f-label)
    (.add f-text)
    (.add fsp-label)
    (.add fsp-text)
    (.add q-label)
    (.add a-label)
    (.add a-text)
    (.add b-label)
    (.add b-text)
    (.add c-label)
    (.add c-text))

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