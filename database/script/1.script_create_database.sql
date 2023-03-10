-- MySQL Script generated by MySQL Workbench
-- Sat Jan 21 11:52:15 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema business
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema business
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `business` DEFAULT CHARACTER SET utf8 ;
USE `business` ;

-- -----------------------------------------------------
-- Table `business`.`enterprises`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `business`.`enterprises` ;

CREATE TABLE IF NOT EXISTS `business`.`enterprises` (
  `id` BIGINT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `created_by` VARCHAR(50) NULL,
  `created_date` TIMESTAMP NULL,
  `modified_by` VARCHAR(50) NULL,
  `modified_date` TIMESTAMP NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business`.`departments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `business`.`departments` ;

CREATE TABLE IF NOT EXISTS `business`.`departments` (
  `id` BIGINT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `created_by` VARCHAR(50) NULL,
  `created_date` TIMESTAMP NULL,
  `modified_by` VARCHAR(50) NULL,
  `modified_date` TIMESTAMP NULL,
  `enterprises_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `enterprises_id`),
  INDEX `fk_departments_enterprises_idx` (`enterprises_id` ASC),
  CONSTRAINT `fk_departments_enterprises`
    FOREIGN KEY (`enterprises_id`)
    REFERENCES `business`.`enterprises` (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business`.`employees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `business`.`employees` ;

CREATE TABLE IF NOT EXISTS `business`.`employees` (
  `id` BIGINT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `age` BIGINT NULL,
  `email` VARCHAR(100) NULL,
  `name` VARCHAR(50) NOT NULL,
  `position` VARCHAR(100) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `created_by` VARCHAR(50) NULL,
  `created_date` TIMESTAMP NULL,
  `modified_by` VARCHAR(50) NULL,
  `modified_date` TIMESTAMP NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business`.`departments_employees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `business`.`departments_employees` ;

CREATE TABLE IF NOT EXISTS `business`.`departments_employees` (
  `id` BIGINT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `created_by` VARCHAR(50) NULL,
  `created_date` TIMESTAMP NULL,
  `modified_by` VARCHAR(50) NULL,
  `modified_date` TIMESTAMP NULL,
  `departments_id` BIGINT NOT NULL,
  `employees_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `departments_id`, `employees_id`),
  INDEX `fk_departments_employees_departments1_idx` (`departments_id` ASC),
  INDEX `fk_departments_employees_employees1_idx` (`employees_id` ASC),
  CONSTRAINT `fk_departments_employees_departments1`
    FOREIGN KEY (`departments_id`)
    REFERENCES `business`.`departments` (`id`),
  CONSTRAINT `fk_departments_employees_employees1`
    FOREIGN KEY (`employees_id`)
    REFERENCES `business`.`employees` (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
