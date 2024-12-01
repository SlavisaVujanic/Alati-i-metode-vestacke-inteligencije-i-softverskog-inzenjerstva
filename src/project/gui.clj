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
  (.setBounds calc-nmm-button 160 300 100 20)


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
    (.setSize 450 800)
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
    (.add calc-nmm-button))

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