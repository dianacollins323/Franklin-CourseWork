;;Diana Collins | Survey of Programming Languages | Lisp Assignment
(defun count-letters (file)
  ;;open file
  (with-open-file (stream file)
    ;;make file stream a string
    (let ((str (make-string (file-length stream)))
    ;;create array to hold occurances of characters in file string  
    (arr (make-array 256 :element-type 'integer :initial-element 0)))
      (read-sequence str stream)
      ;;convert all alpha characters to lowercase and count their occurance
      (loop for c across str do (incf (aref arr (char-code (char-upcase c)))))
       
      ;;compute total number of alpha character
      (let ((total (loop for c from 65 to 90 sum (aref arr c))))
      (format t "The total number of alpha characters in file: ~d~%" total)

      ;;formatted string for table head
      (format t "Letter  |  Occurrences of Letter  |  Relative Frequency~%")
      ;;print alpha characters, their occurances, and their percent frequency
      (loop for c from 65 to 90 for i from 1 do
      (format t "  ~c  |  ~d  |  ~5,2f%~%"
        (code-char c) (aref arr c) (* 100 (/ (aref arr c) total))))))))