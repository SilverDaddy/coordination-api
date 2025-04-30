-- 초기화
DELETE
FROM product;
DELETE
FROM category;
DELETE
FROM brand;

-- 브랜드 삽입
INSERT INTO brand (name, code, deleted)
VALUES ('A', 'A', false);
INSERT INTO brand (name, code, deleted)
VALUES ('B', 'B', false);
INSERT INTO brand (name, code, deleted)
VALUES ('C', 'C', false);
INSERT INTO brand (name, code, deleted)
VALUES ('D', 'D', false);
INSERT INTO brand (name, code, deleted)
VALUES ('E', 'E', false);
INSERT INTO brand (name, code, deleted)
VALUES ('F', 'F', false);
INSERT INTO brand (name, code, deleted)
VALUES ('G', 'G', false);
INSERT INTO brand (name, code, deleted)
VALUES ('H', 'H', false);
INSERT INTO brand (name, code, deleted)
VALUES ('I', 'I', false);

-- 카테고리 삽입
INSERT INTO category (label, code, deleted)
VALUES ('상의', 'TOP', false);
INSERT INTO category (label, code, deleted)
VALUES ('아우터', 'OUTER', false);
INSERT INTO category (label, code, deleted)
VALUES ('바지', 'PANTS', false);
INSERT INTO category (label, code, deleted)
VALUES ('스니커즈', 'SNEAKERS', false);
INSERT INTO category (label, code, deleted)
VALUES ('가방', 'BAG', false);
INSERT INTO category (label, code, deleted)
VALUES ('모자', 'HAT', false);
INSERT INTO category (label, code, deleted)
VALUES ('양말', 'SOCKS', false);
INSERT INTO category (label, code, deleted)
VALUES ('액세서리', 'ACCESSORY', false);

-- 상품 삽입
-- 참고: brand_id = 1 ~ 9, category_id = 1 ~ 8 (순서 주의)
-- model_number : "A-TOP-001" 형식으로 생성

-- A 브랜드 상품
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (1, 1, 'A-상의', 'A-TOP-001', 11200, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (1, 2, 'A-아우터', 'A-OUTER-001', 5500, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (1, 3, 'A-바지', 'A-PANTS-001', 4200, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (1, 4, 'A-스니커즈', 'A-SNEAKERS-001', 9000, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (1, 5, 'A-가방', 'A-BAG-001', 2000, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (1, 6, 'A-모자', 'A-HAT-001', 1700, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (1, 7, 'A-양말', 'A-SOCKS-001', 1800, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (1, 8, 'A-액세서리', 'A-ACCESSORY-001', 2300, false);

-- B 브랜드 상품
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (2, 1, 'B-상의', 'B-TOP-001', 10500, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (2, 2, 'B-아우터', 'B-OUTER-001', 5900, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (2, 3, 'B-바지', 'B-PANTS-001', 3800, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (2, 4, 'B-스니커즈', 'B-SNEAKERS-001', 9100, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (2, 5, 'B-가방', 'B-BAG-001', 2100, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (2, 6, 'B-모자', 'B-HAT-001', 2000, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (2, 7, 'B-양말', 'B-SOCKS-001', 2000, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (2, 8, 'B-액세서리', 'B-ACCESSORY-001', 2200, false);

-- C 브랜드 상품
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (3, 1, 'C-ìી상의', 'C-TOP-001', 10000, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (3, 2, 'C-ì아우터', 'C-OUTER-001', 6200, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (3, 3, 'C-ë°지', 'C-PANTS-001', 3300, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (3, 4, 'C-ì¤°°크', 'C-SNEAKERS-001', 9200, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (3, 5, 'C-¬ê°방', 'C-BAG-001', 2200, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (3, 6, 'C-ëª¨자', 'C-HAT-001', 1900, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (3, 7, 'C-양말', 'C-SOCKS-001', 2200, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (3, 8, 'C-액세서리', 'C-ACCESSORY-001', 2100, false);

-- D 브랜드 상품
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (4, 1, 'D-상의', 'D-TOP-001', 10100, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (4, 2, 'D-아우터', 'D-OUTER-001', 5100, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (4, 3, 'D-바지', 'D-PANTS-001', 3000, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (4, 4, 'D-스니커즈', 'D-SNEAKERS-001', 9500, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (4, 5, 'D-가방', 'D-BAG-001', 2500, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (4, 6, 'D-모자', 'D-HAT-001', 1500, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (4, 7, 'D-양말', 'D-SOCKS-001', 2400, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (4, 8, 'D-액세서리', 'D-ACCESSORY-001', 2000, false);

-- E 브랜드 상품
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (5, 1, 'E-상의', 'E-TOP-001', 10700, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (5, 2, 'E-아우터', 'E-OUTER-001', 5000, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (5, 3, 'E-바지', 'E-PANTS-001', 3800, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (5, 4, 'E-스니커즈', 'E-SNEAKERS-001', 9900, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (5, 5, 'E-가방', 'E-BAG-001', 2300, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (5, 6, 'E-모자', 'E-HAT-001', 1800, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (5, 7, 'E-양말', 'E-SOCKS-001', 2100, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (5, 8, 'E-액세서리', 'E-ACCESSORY-001', 2100, false);

-- F 브랜드 상품
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (6, 1, 'F-상의', 'F-TOP-001', 11200, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (6, 2, 'F-아우터', 'F-OUTER-001', 7200, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (6, 3, 'F-바지', 'F-PANTS-001', 4000, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (6, 4, 'F-스니커즈', 'F-SNEAKERS-001', 9300, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (6, 5, 'F-가방', 'F-BAG-001', 2100, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (6, 6, 'F-모자', 'F-HAT-001', 1600, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (6, 7, 'F-양말', 'F-SOCKS-001', 2300, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (6, 8, 'F-액세서리', 'F-ACCESSORY-001', 1900, false);

-- G 브랜드 상품
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (7, 1, 'G-상의', 'G-TOP-001', 10500, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (7, 2, 'G-아우터', 'G-OUTER-001', 5800, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (7, 3, 'G-바지', 'G-PANTS-001', 3900, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (7, 4, 'G-스니커즈', 'G-SNEAKERS-001', 9000, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (7, 5, 'G-가방', 'G-BAG-001', 2200, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (7, 6, 'G-모자', 'G-HAT-001', 1700, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (7, 7, 'G-양말', 'G-SOCKS-001', 2100, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (7, 8, 'G-액세서리', 'G-ACCESSORY-001', 2000, false);

-- H 브랜드 상품
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (8, 1, 'H-상의', 'H-TOP-001', 10800, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (8, 2, 'H-아우터', 'H-OUTER-001', 6300, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (8, 3, 'H-바지', 'H-PANTS-001', 3100, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (8, 4, 'H-스니커즈', 'H-SNEAKERS-001', 9700, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (8, 5, 'H-가방', 'H-BAG-001', 2100, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (8, 6, 'H-모자', 'H-HAT-001', 1600, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (8, 7, 'H-양말', 'H-SOCKS-001', 2000, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (8, 8, 'H-액세서리', 'H-ACCESSORY-001', 2000, false);

-- I 브랜드 상품
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (9, 1, 'I-상의', 'I-TOP-001', 11400, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (9, 2, 'I-아우터', 'I-OUTER-001', 6700, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (9, 3, 'I-바지', 'I-PANTS-001', 3200, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (9, 4, 'I-스니커즈', 'I-SNEAKERS-001', 9500, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (9, 5, 'I-가방', 'I-BAG-001', 2400, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (9, 6, 'I-모자', 'I-HAT-001', 1700, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (9, 7, 'I-양말', 'I-SOCKS-001', 1700, false);
INSERT INTO product (brand_id, category_id, name, model_number, price, deleted)
VALUES (9, 8, 'I-액세서리', 'I-ACCESSORY-001', 2400, false);
