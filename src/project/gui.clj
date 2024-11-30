(ns project.gui)
(import '(javax.swing JFrame JLabel JTextField JButton)
        '(java.awt.event ActionListener)
        '(java.awt GridLayout))

(let [main-frame (new JFrame "Aircraft Performance")
      main-nmm-button (new JButton "NMM")
      main-tmm-button (new JButton "TMM")
      main-compare (new JButton "Compare")

      nmm-frame (new JFrame "NMM")]

  (.setLayout main-frame nil)
  (.setBounds main-nmm-button 0 0 100 50)
  (.setBounds main-compare 110 0 100 50)
  (.setBounds main-tmm-button 220 0 100 50)


  (doto main-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 330 87)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible true)
    (.add main-nmm-button)
    (.add main-compare)
    (.add main-tmm-button))

  (doto nmm-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 330 87)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible false)))