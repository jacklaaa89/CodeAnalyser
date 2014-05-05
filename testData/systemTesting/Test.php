<?php

namespace Tester;


class Tester {
    
    public function __construct() {
        var_dump(new DateTime());
    }
    
    public function testerMethod2($testervariable, $anotherTesterVariable) {
        return array($testervariable => $anotherTesterVariable);
    }
    
}