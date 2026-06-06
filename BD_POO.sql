DROP DATABASE IF EXISTS pizzaria;
CREATE DATABASE pizzaria;
USE pizzaria;
 
-- create
CREATE TABLE cliente (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  telefone varchar(100) NOT NULL,
  endereco varchar(255) NOT NULL
);
 
CREATE TABLE pedido (
  id INT PRIMARY KEY AUTO_INCREMENT,
  data_pedido VARCHAR(20),
  valor_total float,
  status varchar(30),
  id_cliente INT,

  constraint fk_pedido_cliente
  foreign KEY (id_cliente) references cliente(id)
);
 
CREATE TABLE pizza(
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome varchar (30),
  descricao varchar(255),
  preco float,
  tamanho varchar(15)
);
 
CREATE TABLE item_pedido(
  id INT PRIMARY KEY AUTO_INCREMENT,
  quantidade int,
  sub_total float,
  id_pedido INT,
  id_pizza INT,

  constraint fk_itempedido_pedido
  foreign KEY (id_pedido) references pedido(id),

  constraint fk_itempedido_pizza
  foreign KEY (id_pizza) references pizza(id)
);
 
CREATE TABLE pagamento(
  id INT PRIMARY KEY AUTO_INCREMENT,
  tipo varchar(30),
  valor_pagamento float,
  data_pagamento date,
  id_pedido INT,
  
  constraint fk_pagamento_pedido
  foreign KEY (id_pedido) references Pedido(id)
);
 
 
CREATE TABLE ingrediente(
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(30),
  quantidade_estoque INT,
  unidade VARCHAR(20)
);