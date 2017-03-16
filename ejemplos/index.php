<?php

	class Facebook{
		public $nombre;
		public $edad;
		private $pass;

		public function __construct($nombre, $edad, $pass){
			$this->nombre = $nombre;
			$this->edad = $edad;
			$this->pass = $pass;
		}

		public function verInformacion(){
			echo "Nombre: ". $this->nombre . "<br>";
			echo "Edad: ". $this->edad . "<br>";
			echo "Password: ". $this->pass . "<br>";
		}

		private function cambiarPass($pass){
			$this->pass = $pass;
		}
	}

	$facebook = new Facebook("Sandy Ramos", "23", "password");
	$facebook->verInformacion();

	class Pagina{
		public $nombre = "Codigo facilito";
		public static $url = "www.codigofacilito.com";

		public function bienvenido(){
			echo "Bienvenidos a <b> " . $this->nombre . "</b> la página es " . self::$url; 
		}

		public static function bienvenida2(){
			echo "Bienvenidos a " . self::$url;
		}
	}

	$pagina = new Pagina();
	$pagina->bienvenido();

	Pagina::bienvenida2();

	interface Auto{
		public function encender();
		public function apagar();
	}

	interface Gasolina extends Auto{
		public function vaciarTanque();
		public function llenarTanque();
	}

	class Deportivo implements Gasolina{
		
		public function ver(){
			echo "Hola";
		}

		public function encender(){

		}

		public function apagar(){

		}

		public function vaciarTanque(){

		}

		public function llenarTanque(){

		}

	}

	$obj = new Deportivo();
	$obj->ver();


	abstract class Molde{
		abstract public function ingresarNombre();
		abstract public function obtenerNombre();
	}

	//Trabajando con traits
	trait Persona1{
		public $nombre;

		public function mostrarNombre(){
			echo $this->$nombre();
		}

		abstract function asignarNombre($nombre);
	}

	trait Trabajador{
		protected function mensaje(){
			echo "y soy un trabajador";
		}
	}

	class Persona{
		use Persona1;
		use Trabajador;

		public function asignarNombre($nombre){
			$this->nombre = $nombre . self::mensaje();
		}
	}

	$persona = new Persona();
	$persona->asignarNombre("Sandy");
	echo $persona->nombre;

	//Trabajando con namespaces
	#



	spl_autoload_register(function($clase){
		$ruta = "api/" . str_replace("\\", "/", $clase) . ".php";
		if(is_readable($ruta)){
			require_once($ruta);
		}else{
			echo "El archivo no existe";
		}
	});

	Models\Persona::hola();
?>