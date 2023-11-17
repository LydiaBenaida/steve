-- Table structure for table `pdl`
--
CREATE TABLE `pdl` (
                       `ref_PDL` INT(11) NOT NULL AUTO_INCREMENT,
                       `station_pk` VARCHAR(30) NOT NULL COLLATE 'latin1_swedish_ci',
                       `power` VARCHAR(3) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
                       `dateActivation` TIMESTAMP NULL DEFAULT current_timestamp(),
                       PRIMARY KEY (`ref_PDL`) USING BTREE,
                       UNIQUE INDEX `ref_PDL_UNIQUE` (`ref_PDL`) USING BTREE
)
    COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
