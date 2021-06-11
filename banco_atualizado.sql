-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 11-Jun-2021 às 22:22
-- Versão do servidor: 5.7.25
-- versão do PHP: 7.1.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banco_atualizado`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cargo`
--

CREATE TABLE `cargo` (
  `idCargo` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `setor` varchar(30) NOT NULL,
  `salario` decimal(7,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONSHIPS FOR TABLE `cargo`:
--   `idCargo`
--       `usuario` -> `idCargo`
--

--
-- Extraindo dados da tabela `cargo`
--

INSERT INTO `cargo` (`idCargo`, `nome`, `setor`, `salario`) VALUES
(1, 'Gerente', 'Projetos', '5000.00'),
(2, 'Auxiliar de Projetos', 'Projetos', '2000.00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `equipe`
--

CREATE TABLE `equipe` (
  `idE` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONSHIPS FOR TABLE `equipe`:
--

--
-- Extraindo dados da tabela `equipe`
--

INSERT INTO `equipe` (`idE`, `nome`) VALUES
(1, 'Auditores'),
(2, 'Solucionadores');

-- --------------------------------------------------------

--
-- Estrutura da tabela `nc`
--

CREATE TABLE `nc` (
  `idNC` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descricao` varchar(300) NOT NULL,
  `dataCriacao` date NOT NULL,
  `dataTermino` date DEFAULT NULL,
  `pdf` varbinary(5000) DEFAULT NULL,
  `fotos` varbinary(100) DEFAULT NULL,
  `resolucao` longtext,
  `prioridades` int(11) NOT NULL,
  `idU` int(11) DEFAULT NULL,
  `idE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONSHIPS FOR TABLE `nc`:
--   `idU`
--       `usuario` -> `idU`
--   `idE`
--       `equipe` -> `idE`
--

--
-- Extraindo dados da tabela `nc`
--

INSERT INTO `nc` (`idNC`, `nome`, `descricao`, `dataCriacao`, `dataTermino`, `pdf`, `fotos`, `resolucao`, `prioridades`, `idU`, `idE`) VALUES
(1, 'Problemas nas escadas', 'Muitos acidentes ocorrendo nas escadas', '2021-05-25', '2021-05-26', NULL, NULL, 'Colocar Borrachas nos pisos .\n Colocar corrimão.\n Colocar avisos.', 5, 2, 2),
(2, 'Problema nas pias', 'Pias entupidas de cabelos e papel', '2021-05-31', NULL, NULL, NULL, NULL, 4, NULL, 2),
(3, 'Problemas nos vasos sanitários', 'Os vasos estão entupindo constantemente com cocô e papel.', '2021-05-31', NULL, NULL, NULL, NULL, 5, 1, 2),
(4, 'Fogos de artificio lançado dentro da instalações', 'Pessoal não autorizado, que não  faz parte da empresa, soltando fogos de artificio dentro do prédio.', '2021-05-31', NULL, NULL, NULL, NULL, 5, NULL, NULL),
(6, 'Problemas de iluminação', 'Corredores e escritórios mau iluminados estão causando acidentes.', '2021-06-09', NULL, NULL, NULL, NULL, 5, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `idU` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dataNascimento` date NOT NULL,
  `CPF` bigint(20) NOT NULL,
  `PIS` bigint(20) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `idCargo` int(11) NOT NULL,
  `idE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONSHIPS FOR TABLE `usuario`:
--   `idE`
--       `equipe` -> `idE`
--

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`idU`, `nome`, `email`, `dataNascimento`, `CPF`, `PIS`, `senha`, `idCargo`, `idE`) VALUES
(1, 'João Pedroso', 'joao@email.com.br', '1985-01-08', 1, 1, '321', 1, 1),
(2, 'Rodrigo da Silva', 'rodrigo@email.com.br', '1989-12-25', 2, 2, '123', 2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`idCargo`);

--
-- Indexes for table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`idE`);

--
-- Indexes for table `nc`
--
ALTER TABLE `nc`
  ADD PRIMARY KEY (`idNC`),
  ADD KEY `Equipe` (`idE`),
  ADD KEY `Usuario` (`idU`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idU`),
  ADD KEY `Cargo` (`idCargo`),
  ADD KEY `Equipe` (`idE`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cargo`
--
ALTER TABLE `cargo`
  MODIFY `idCargo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `equipe`
--
ALTER TABLE `equipe`
  MODIFY `idE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `nc`
--
ALTER TABLE `nc`
  MODIFY `idNC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idU` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `cargo`
--
ALTER TABLE `cargo`
  ADD CONSTRAINT `cargo_ibfk_1` FOREIGN KEY (`idCargo`) REFERENCES `usuario` (`idCargo`);

--
-- Limitadores para a tabela `nc`
--
ALTER TABLE `nc`
  ADD CONSTRAINT `nc_ibfk_1` FOREIGN KEY (`idU`) REFERENCES `usuario` (`idU`),
  ADD CONSTRAINT `nc_ibfk_2` FOREIGN KEY (`idE`) REFERENCES `equipe` (`idE`);

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idE`) REFERENCES `equipe` (`idE`);


--
-- Metadata
--
USE `phpmyadmin`;

--
-- Metadata for table cargo
--

--
-- Metadata for table equipe
--

--
-- Metadata for table nc
--

--
-- Extraindo dados da tabela `pma__column_info`
--

INSERT INTO `pma__column_info` (`db_name`, `table_name`, `column_name`, `comment`, `mimetype`, `transformation`, `transformation_options`, `input_transformation`, `input_transformation_options`) VALUES
('banco_atualizado', 'nc', 'fotos', '', '', 'output/image_jpeg_link.php', '', '', ''),
('banco_atualizado', 'nc', 'pdf', '', '', 'output/application_octetstream_download.php', '', '', '');

--
-- Metadata for table usuario
--

--
-- Metadata for database banco_atualizado
--
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
