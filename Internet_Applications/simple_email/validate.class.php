<?php

class validate {

    //create errors array
    public $errors = array();

    //validate any string
    //$postVal - value posted for the form element
    //$postName - name of the form element
    //$min - minimum length of string
    //$max - maximum length of string
    public function validateStr($postVal, $postName, $min = 5, $max = 500) {
        if(strlen($postVal) < intval($min)) {
            $this->setError($postName, ucfirst($postName)." must be at least {$min} characters long.");
        }   
        else if(strlen($postVal) > intval($max)) {
            $this->setError($postName, ucfirst($postName)." must be less than {$max} characters long.");
        }
    }

    //validate email address
    //$emailVal - value posted for the fomr element
    //$emailName - name of the form element
    public function validateEmail($emailVal, $emailName) {
        if(strlen($emailVal) <= 0) {
            $this->setError($emailName, "Please enter an Email Address");
        }
        else if (!preg_match('/^[^0-9][a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[@][a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,4}$/', $emailVal)) {
            $this->setError($emailName, "Please enter a Valid Email Address");
        }
    }

    //sets error array
    //$element - element name the didnt' validate
    //$message - error message
    private function setError($element, $message) {
        $this->errors[$element] = $message;
    }

    //returns error message for element
    //$elementName - name of element
    public function getError($elementName) {
        if($this->errors[$elementName]) {
            return $this->errors[$elementName];
        }
        else {
            return false;
        }
    }

    //displays all errors in ul
    public function displayErrors() {
        $errorsList = "<ul class=\"errors\">\n";
        foreach($this->errors as $value) {
            $errorsList .= "<li>". $value . "</li>\n";
        }
        $errorsList .= "</ul>\n";
        return $errorsList;
    }

    //checks if the form has errors
    public function hasErrors() {
        if(count($this->errors) > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public function errorNumMessage() {
        if(count($this->errors) > 1) {
            $message = "There were " . count($this->errors) . " errors sending your message!\n";
        }
        else {
            $message = "There was an error sending your message!\n";
        }
        return $message;
    }
}


?>