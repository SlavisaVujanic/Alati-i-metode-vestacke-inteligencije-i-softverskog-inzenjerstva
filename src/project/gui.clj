(ns project.gui)
(import '(javax.swing JFrame JLabel JTextField JButton)
        '(java.awt.event ActionListener)
        '(java.awt GridLayout))

(let [frame (new JFrame "Aircraft Performance")
      par1-label (new JLabel "Par1")
      par2-label (new JLabel "Par2")
      par3-label (new JLabel "Par3")
      button (new JButton "Btn")]


  (doto frame
    (.setLayout (new GridLayout 3 1))
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 640 360)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible true)
    (. add par1-label)
    (. add par2-label)
    (. add par3-label)))