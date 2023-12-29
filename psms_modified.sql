-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema psms
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema psms
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `psms` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `psms` ;

-- -----------------------------------------------------
-- Table `psms`.`adopter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `psms`.`adopter` (
  `adopter_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NULL DEFAULT NULL,
  `phone` VARCHAR(20) NULL DEFAULT NULL,
  `email` VARCHAR(30) NULL DEFAULT NULL,
  `password` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`adopter_id`),
  UNIQUE INDEX `uniqueEmailConstraint` (`email` ASC) VISIBLE,
  UNIQUE INDEX `uniquePhoneConstraint` (`phone` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `psms`.`adoptionrecord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `psms`.`adoptionrecord` (
  `pet_id` INT NOT NULL AUTO_INCREMENT,
  `adopter_id` INT NULL DEFAULT NULL,
  `adoption_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`pet_id`),
  INDEX `userAdoptedPetConstraint` (`adopter_id` ASC) VISIBLE,
  CONSTRAINT `userAdoptedPetConstraint`
    FOREIGN KEY (`adopter_id`)
    REFERENCES `psms`.`adopter` (`adopter_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `psms`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `psms`.`staff` (
  `staff_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NULL DEFAULT NULL,
  `role` VARCHAR(20) NULL DEFAULT NULL,
  `phone` VARCHAR(20) NULL DEFAULT NULL,
  `email` VARCHAR(30) NULL DEFAULT NULL,
  `password` VARCHAR(200) NULL DEFAULT NULL,
  `shelter_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE INDEX `uniqueEmailConstraint` (`email` ASC) VISIBLE,
  UNIQUE INDEX `uniquePhoneConstraint` (`phone` ASC) VISIBLE,
  INDEX `shelterOfStaffConstraint` (`shelter_id` ASC) VISIBLE,
  CONSTRAINT `shelterOfStaffConstraint`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `psms`.`shelter` (`shelter_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `psms`.`shelter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `psms`.`shelter` (
  `shelter_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NULL DEFAULT NULL,
  `country` VARCHAR(20) NULL DEFAULT NULL,
  `city` VARCHAR(20) NULL DEFAULT NULL,
  `phone` VARCHAR(20) NULL DEFAULT NULL,
  `email` VARCHAR(30) NULL DEFAULT NULL,
  `detailed_address` VARCHAR(50) NULL DEFAULT NULL,
  `manager_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`shelter_id`),
  UNIQUE INDEX `uniqueEmailConstraint` (`email` ASC) VISIBLE,
  UNIQUE INDEX `uniquePhoneConstraint` (`phone` ASC) VISIBLE,
  INDEX `shelterManagerConstraint` (`manager_id` ASC) VISIBLE,
  CONSTRAINT `shelterManagerConstraint`
    FOREIGN KEY (`manager_id`)
    REFERENCES `psms`.`staff` (`staff_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `psms`.`pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `psms`.`pet` (
  `pet_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `gender` TINYINT(1) NULL DEFAULT NULL,
  `health_status` VARCHAR(30) NULL DEFAULT NULL,
  `species` VARCHAR(20) NULL DEFAULT NULL,
  `breed` VARCHAR(20) NULL DEFAULT NULL,
  `behavior` VARCHAR(50) NULL DEFAULT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `shelter_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`pet_id`),
  INDEX `shelterOfPetConstraint` (`shelter_id` ASC) VISIBLE,
  CONSTRAINT `shelterOfPetConstraint`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `psms`.`shelter` (`shelter_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `psms`.`application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `psms`.`application` (
  `adopter_id` INT NOT NULL,
  `pet_id` INT NOT NULL,
  `status` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`adopter_id`, `pet_id`),
  INDEX `petInApplicationConstraint` (`pet_id` ASC) VISIBLE,
  CONSTRAINT `petInApplicationConstraint`
    FOREIGN KEY (`pet_id`)
    REFERENCES `psms`.`pet` (`pet_id`),
  CONSTRAINT `userInApplicationConstraint`
    FOREIGN KEY (`adopter_id`)
    REFERENCES `psms`.`adopter` (`adopter_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `psms`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `psms`.`users` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `psms`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `psms`.`authorities` (
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  UNIQUE INDEX `authorities_idx_1` (`username` ASC, `authority` ASC),
  CONSTRAINT `authorities_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `psms`.`users` (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `psms`.`petdoc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `psms`.`petdoc` (
  `document` VARCHAR(100) NOT NULL,
  `pet_id` INT NULL DEFAULT NULL,
  `type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`document`),
  INDEX `petOwnerOfDocumentConstraint` (`pet_id` ASC) VISIBLE,
  CONSTRAINT `petOwnerOfDocumentConstraint`
    FOREIGN KEY (`pet_id`)
    REFERENCES `psms`.`pet` (`pet_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `psms`.`petimages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `psms`.`petimages` (
  `image` VARCHAR(100) NOT NULL,
  `pet_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`image`),
  INDEX `petOwnerOfImageConstraint` (`pet_id` ASC) VISIBLE,
  CONSTRAINT `petOwnerOfImageConstraint`
    FOREIGN KEY (`pet_id`)
    REFERENCES `psms`.`pet` (`pet_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `psms`;

DELIMITER $$
USE `psms`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `psms`.`set_shelter_id_for_manager`
AFTER INSERT ON `psms`.`shelter`
FOR EACH ROW
begin
	update staff set staff.shelter_id = new.shelter_id
    where staff.staff_id = new.manager_id;
end$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;