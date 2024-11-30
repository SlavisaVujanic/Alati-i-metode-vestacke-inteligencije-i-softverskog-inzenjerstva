(ns project.gui)
(import '(javax.swing JFrame JLabel JTextField JButton)
        '(java.awt.event ActionListener))

(let [frame (new JFrame "Aircraft Performance")]
  (doto frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 640 360)
    (.setVisible true)))

