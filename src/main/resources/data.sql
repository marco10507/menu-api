INSERT INTO MENU (ID ,NAME, DESCRIPTION)
VALUES (1, 'Italian Menu', 'Homemade Italian cuisine');


INSERT INTO MENU_SECTION (ID ,DESCRIPTION ,NAME ,MENU_ID )
VALUES (1, 'Best fresh paste in the world', 'Fresh homemade pasta', 1);

INSERT INTO MENU_SECTION (ID ,DESCRIPTION ,NAME ,MENU_ID )
VALUES (2, 'Best salads in the world', 'Salads', 1);


INSERT INTO MENU_ITEM  (ID , NAME,  DESCRIPTION , PICTURE_LINK , PRICE, CURRENCY)
VALUES (1, 'Tagliatelle al salmone',  'Thick pasta with a creamy salmon and parsley sauce', 'https://www.cucchiaio.it/content/cucchiaio/it/ricette/2012/05/ricetta-tagliatelle-salmone/jcr:content/header-par/image_single.img10.jpg/1579002482123.jpg', 15, 'eur');

INSERT INTO MENU_ITEM  (ID , NAME,  DESCRIPTION , PICTURE_LINK , PRICE, CURRENCY)
VALUES (2, 'Cannelloni ricotta e spinaci',  'Pasta rolls filled with ricotta and spinach with bechamel sauce, tomatoes and Parmesan cheese', 'https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/E6AD9B48-4A30-41C0-9955-6C1EBEECDD79/Derivates/5966AC8A-0252-46CE-AF8A-8EF0E79AB458.jpg', 14.50, 'eur');

INSERT INTO MENU_ITEM  (ID , NAME,  DESCRIPTION , PICTURE_LINK , PRICE, CURRENCY)
VALUES (3, 'Caprese salade',  'Buffalo Mozzarella, cherry tomatoes, pesto and balsamic glaze', 'https://www.cookingclassy.com/wp-content/uploads/2020/07/caprese-salad-33-500x500.jpg', 10, 'eur');

INSERT INTO MENU_ITEM  (ID , NAME,  DESCRIPTION , PICTURE_LINK , PRICE, CURRENCY)
VALUES (4, 'Parma salad',  'Parma ham, Gorgonzola, walnuts and glaze balsamic', 'https://karlijnskitchen.com/wp-content/uploads/2016/08/Italiaanse-salade-met-burrata-en-parmaham-4.jpg', 10, 'eur');


INSERT INTO MENU_SECTION_ITEM (ID  ,MENU_ITEM_ID ,MENU_SECTION_ID )
VALUES (1, 1,1);

INSERT INTO MENU_SECTION_ITEM (ID  ,MENU_ITEM_ID ,MENU_SECTION_ID )
VALUES (2, 2,1);

INSERT INTO MENU_SECTION_ITEM (ID  ,MENU_ITEM_ID ,MENU_SECTION_ID )
VALUES (3, 3,2);

INSERT INTO MENU_SECTION_ITEM (ID  ,MENU_ITEM_ID ,MENU_SECTION_ID )
VALUES (4, 4,2);

