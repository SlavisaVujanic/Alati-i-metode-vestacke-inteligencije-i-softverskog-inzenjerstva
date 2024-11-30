(ns project.gui)
(import '(javax.swing JFrame JLabel JTextField JButton)
        '(java.awt.event ActionListener)
        '(java.awt GridLayout))

(let [frame (new JFrame "Aircraft Performance")
      par1-label (new JLabel "Par1")
      par2-label (new JLabel "Par2")
      par3-label (new JLabel "Par3")
      button (new JButton "Btn")]

  (.setLayout frame nil)

  (.setBounds par1-label 0 0 50 50)
  (.setBounds par2-label 50 0 50 50)
  (.setBounds par3-label 100 0 50 50)
  (.setBounds button 200 100 100 50)


  (doto frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 640 360)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible true)
    (.add par1-label)
    (.add par2-label)
    (.add par3-label)
    (.add button)))