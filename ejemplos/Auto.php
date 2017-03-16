<?php
	
	$_GET['action'];

	function autoload($clase){
		include $_GET['action'] . "/" . $clase . ".php";
	}

	spl_autoload_register('autoload');

	Persona::mostrar("Hola mundo");
?>