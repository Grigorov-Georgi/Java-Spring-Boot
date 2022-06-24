-- some test users
INSERT INTO users (id, email, first_name, last_name, image_url, is_active, password)
VALUES (1, 'misho@mail.com', 'Misho', 'Mishov', null, 1, 'cc0c9b14bbd6767d508f992230be826d5422a705303dd1052104c6b631a18660edc53c24c437c167');

INSERT INTO brands (id, name)
VALUES (1, 'Mercedes-Benz'),
       (2, 'BMW');

INSERT INTO models (id, name, category, start_year, end_year, brand_id, image_url)
VALUES (1, 'E-class', 'CAR', 1980, null, 1, 'https://www.mercedes-benz.bg/passengercars/mercedes-benz-cars/models/e-class/saloon-w213-fl/explore/design-teaser/_jcr_content/par/teaserbox/teaserelement1/teaserelement/image.MQ6.6.20200717130143.jpeg'),
       (2, '5-series', 'CAR', 1981, null, 2, 'https://cdn.motor1.com/images/mgl/9o4QG/s1/2021-bmw-5-series.jpg'),
       (3, '6-series', 'CAR', 1992, 2018, 2, 'https://cdn.bmwblog.com/wp-content/uploads/2021/05/6-Series-GT.jpg'),
       (4, 'S-class', 'CAR', 1966, null, 1, 'https://www.topgear.com/sites/default/files/2022/03/1-Mercedes-S-Class-plug-in.jpg');