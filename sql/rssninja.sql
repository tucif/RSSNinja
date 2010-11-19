SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `RSSNinja` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `RSSNinja` ;

-- -----------------------------------------------------
-- Table `RSSNinja`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RSSNinja`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RSSNinja`.`keyword`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RSSNinja`.`keyword` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `value` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RSSNinja`.`user_keyword`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RSSNinja`.`user_keyword` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NULL ,
  `keyword_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_user_keyword_1` (`user_id` ASC) ,
  INDEX `fk_user_keyword_2` (`keyword_id` ASC) ,
  CONSTRAINT `fk_user_keyword_1`
    FOREIGN KEY (`user_id` )
    REFERENCES `RSSNinja`.`user` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_keyword_2`
    FOREIGN KEY (`keyword_id` )
    REFERENCES `RSSNinja`.`keyword` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RSSNinja`.`link`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RSSNinja`.`link` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `value` VARCHAR(500) NULL ,
  `fecha` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RSSNinja`.`knowledge`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RSSNinja`.`knowledge` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `link` INT NULL ,
  `keyword` INT NULL ,
  `servicio` VARCHAR(100) NULL ,
  `relevancia` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_knowledge_1` (`link` ASC) ,
  CONSTRAINT `fk_knowledge_1`
    FOREIGN KEY (`link` )
    REFERENCES `RSSNinja`.`link` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RSSNinja`.`knowledge_user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RSSNinja`.`knowledge_user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_knowledge` INT NULL ,
  `id_user` INT NULL ,
  `rating` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_knowledge_user_1` (`id_knowledge` ASC) ,
  INDEX `fk_knowledge_user_2` (`id_user` ASC) ,
  CONSTRAINT `fk_knowledge_user_1`
    FOREIGN KEY (`id_knowledge` )
    REFERENCES `RSSNinja`.`knowledge` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_knowledge_user_2`
    FOREIGN KEY (`id_user` )
    REFERENCES `RSSNinja`.`user` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RSSNinja`.`word`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RSSNinja`.`word` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `value` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RSSNinja`.`Semantic`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RSSNinja`.`Semantic` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `word1` INT NULL ,
  `word2` INT NULL ,
  `relation_factor` FLOAT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Semantic_1` (`word1` ASC) ,
  INDEX `fk_Semantic_2` (`word2` ASC) ,
  CONSTRAINT `fk_Semantic_1`
    FOREIGN KEY (`word1` )
    REFERENCES `RSSNinja`.`word` (`id` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Semantic_2`
    FOREIGN KEY (`word2` )
    REFERENCES `RSSNinja`.`word` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
