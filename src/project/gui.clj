(ns project.gui)
(import '(javax.swing JFrame JLabel JTextField JButton)
        '(java.awt.event ActionListener)
        '(java.awt GridLayout))

(let [main-frame (new JFrame "Aircraft Performance")
      main-nmm-button (new JButton "NMM")
      main-tmm-button (new JButton "TMM")
      main-compare-button (new JButton "Compare")

      nmm-frame (new JFrame "NMM")
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
    (.setSize 330 87)
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