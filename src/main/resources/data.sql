-- Insert sample destinations
INSERT INTO destinations (name, description, region, image_url, best_time_to_visit, average_temperature, travel_tips, is_featured, is_active, latitude, longitude, altitude, popularity_rating) VALUES
('Sigiriya Rock Fortress', 'Ancient rock fortress and palace ruins with famous frescoes and water gardens. A UNESCO World Heritage Site that showcases ancient Sri Lankan architecture and artistry. The rock stands 200 meters high and offers breathtaking panoramic views of the surrounding jungle.', 'Central Province', '/images/sigiriya.jpg', 'December to April', '27-32°C', 'Climb early morning or late afternoon to avoid heat. Wear comfortable shoes. Carry water. Don''t miss the mirror wall and frescoes.', true, true, 7.9570, 80.7603, '370m', 5),
('Ella', 'Mountain town with stunning views, hiking trails, and the famous Nine Arch Bridge. Perfect for nature lovers and adventure seekers in the hill country. Experience cool climate, tea plantations, and beautiful waterfalls.', 'Uva Province', '/images/ella.jpg', 'January to March', '18-24°C', 'Carry a light jacket. Best for hiking and photography. Visit early morning for clear views of the hills.', true, true, 6.8690, 81.0460, '1041m', 4),
('Galle Fort', 'Historical fortified city with cobblestone streets, colonial architecture, and boutique shops. A UNESCO World Heritage Site with Dutch colonial influence. Explore museums, churches, and enjoy sunset views from the ramparts.', 'Southern Province', '/images/galle.jpg', 'November to April', '26-30°C', 'Wear comfortable walking shoes. Best explored on foot. Visit the lighthouse and maritime museum.', true, true, 6.0267, 80.2166, '0-10m', 4),
('Kandy', 'Sacred city with the Temple of the Tooth Relic, beautiful Kandy Lake, and rich cultural heritage. The last capital of the Sri Lankan kings and a UNESCO World Heritage Site.', 'Central Province', '/images/kandy.jpg', 'December to April', '20-28°C', 'Dress modestly for temple visits. Attend cultural shows. Visit the botanical gardens in Peradeniya.', true, true, 7.2906, 80.6337, '500m', 5),
('Mirissa', 'Beautiful beach destination known for whale watching, surfing, and stunning sunsets. A tropical paradise on Sri Lanka''s southern coast with golden sandy beaches and coconut palms.', 'Southern Province', '/images/mirissa.jpg', 'November to April', '26-30°C', 'Use sunscreen. Best for whale watching December to April. Try local seafood. Respect marine life.', true, true, 5.9490, 80.4583, '0-5m', 4),
('Nuwara Eliya', 'Scenic hill country with tea plantations, cool climate, and colonial-era architecture. Known as "Little England" for its British colonial influence. Enjoy golfing, horse riding, and visiting tea factories.', 'Central Province', '/images/nuwara-eliya.jpg', 'March to May', '16-22°C', 'Carry warm clothing. Visit tea factories. Try fresh strawberries. Enjoy boat rides in Gregory Lake.', true, true, 6.9497, 80.7891, '1868m', 4),
('Yala National Park', 'Famous wildlife sanctuary known for leopards, elephants, and diverse bird species. One of the best places in the world for leopard sightings with varied ecosystems including forests, grasslands, and lagoons.', 'Southern Province', '/images/yala.jpg', 'February to July', '26-30°C', 'Book safari in advance. Early morning safaris best for wildlife spotting. Carry binoculars and camera.', false, true, 6.3728, 81.5150, '30-125m', 3),
('Anuradhapura', 'Ancient capital and UNESCO World Heritage Site with magnificent dagobas, palaces, and monasteries. One of the oldest continuously inhabited cities in the world and a major Buddhist pilgrimage site.', 'North Central Province', '/images/anuradhapura.jpg', 'April to September', '27-33°C', 'Rent a bicycle to explore. Dress modestly for religious sites. Visit the sacred bodhi tree.', false, true, 8.3356, 80.3880, '100m', 3),
('Polonnaruwa', 'Medieval capital with well-preserved ancient city ruins, Buddha statues, and irrigation systems. A UNESCO World Heritage Site showcasing advanced ancient Sri Lankan civilization.', 'North Central Province', '/images/polonnaruwa.jpg', 'April to September', '27-33°C', 'Hire a guide for historical context. Best explored by bicycle. Visit the archaeological museum.', false, true, 7.9320, 81.0080, '60m', 3),
('Trincomalee', 'Natural deep-water harbor with beautiful beaches, coral reefs, and historic Hindu temples. Famous for whale watching, diving, and the sacred Koneswaram Temple.', 'Eastern Province', '/images/trincomalee.jpg', 'May to September', '27-32°C', 'Best for diving and snorkeling. Visit the war cemetery. Try fresh seafood at the beach.', false, true, 8.5870, 81.2150, '0-8m', 2);

-- Insert sample tour packages
INSERT INTO tour_packages (name, description, price, duration, difficulty, destination_id, max_travelers, start_date, end_date, image_url) VALUES
('Sigiriya Rock Climbing Experience', 'Experience the ancient wonder of Sigiriya with a guided climb to the top of the rock fortress. Learn about its history and enjoy breathtaking views.', 75.00, 1, 'MODERATE', 1, 15, '2023-10-15', '2023-12-15', '/images/sigiriya-tour.jpg'),
('Ella Hiking Adventure', 'Explore the beautiful landscapes of Ella with hikes to Little Adams Peak, Nine Arch Bridge, and Ravana Falls. Perfect for nature lovers.', 120.00, 2, 'MODERATE', 2, 12, '2023-10-10', '2023-11-30', '/images/ella-tour.jpg'),
('Galle Fort Cultural Walk', 'Discover the colonial heritage of Galle Fort with a guided walking tour. Visit historical sites, boutique shops, and enjoy local cuisine.', 60.00, 1, 'EASY', 3, 20, '2023-10-01', '2023-12-31', '/images/galle-tour.jpg'),
('Kandy Cultural Experience', 'Visit the sacred Temple of the Tooth, explore the Royal Botanical Gardens, and enjoy a traditional cultural show.', 90.00, 1, 'EASY', 4, 25, '2023-10-05', '2023-12-20', '/images/kandy-tour.jpg'),
('Mirissa Whale Watching', 'Experience the amazing opportunity to see blue whales and dolphins in their natural habitat on this early morning whale watching tour.', 85.00, 1, 'EASY', 5, 30, '2023-11-01', '2024-04-30', '/images/mirissa-tour.jpg'),
('Nuwara Eliya Tea Plantation Tour', 'Visit tea plantations, learn about tea production, and enjoy tea tasting sessions in the beautiful hill country of Nuwara Eliya.', 70.00, 1, 'EASY', 6, 18, '2023-10-01', '2023-12-31', '/images/tea-tour.jpg');

-- Insert sample users
INSERT INTO users (name, email, password, role) VALUES
('Admin User', 'admin@example.com', 'password', 'ADMIN'),
('John Doe', 'john@example.com', 'password', 'USER'),
('Jane Smith', 'jane@example.com', 'password', 'USER');

-- Insert destination activities
INSERT INTO destination_activities (destination_id, activity) VALUES
(1, 'Historical Tours'), (1, 'Rock Climbing'), (1, 'Photography'), (1, 'Cultural Experience'),
(2, 'Hiking'), (2, 'Train Rides'), (2, 'Tea Plantation Visits'), (2, 'Waterfall Exploration'),
(3, 'Walking Tours'), (3, 'Shopping'), (3, 'Historical Sites'), (3, 'Sunset Views'),
(4, 'Temple Visits'), (4, 'Cultural Shows'), (4, 'Botanical Gardens'), (4, 'Lake Activities'),
(5, 'Whale Watching'), (5, 'Surfing'), (5, 'Beach Relaxation'), (5, 'Snorkeling'),
(6, 'Tea Factory Tours'), (6, 'Golfing'), (6, 'Horse Riding'), (6, 'Boating'),
(7, 'Wildlife Safaris'), (7, 'Bird Watching'), (7, 'Nature Photography'), (7, 'Camping'),
(8, 'Archaeological Tours'), (8, 'Bicycle Tours'), (8, 'Pilgrimage'), (8, 'Historical Sites'),
(9, 'Archaeological Exploration'), (9, 'Bicycle Tours'), (9, 'Museum Visits'), (9, 'Historical Photography'),
(10, 'Diving'), (10, 'Snorkeling'), (10, 'Whale Watching'), (10, 'Temple Visits');

-- Insert destination highlights
INSERT INTO destination_highlights (destination_id, highlight) VALUES
(1, 'UNESCO World Heritage Site'), (1, 'Ancient Frescoes'), (1, 'Water Gardens'), (1, '360° Panoramic Views'),
(2, 'Nine Arch Bridge'), (2, 'Ella Rock'), (2, 'Little Adams Peak'), (2, 'Ravana Falls'),
(3, 'Dutch Colonial Architecture'), (3, 'Galle Lighthouse'), (3, 'Historical Ramparts'), (3, 'Boutique Shopping'),
(4, 'Temple of the Tooth Relic'), (4, 'Kandy Lake'), (4, 'Cultural Dance Shows'), (4, 'Royal Botanical Gardens'),
(5, 'Whale Watching Tours'), (5, 'Secret Beach'), (5, 'Coconut Tree Hill'), (5, 'Surfing Lessons'),
(6, 'Tea Plantations'), (6, 'Gregory Lake'), (6, 'Victoria Park'), (6, 'Golf Course'),
(7, 'Leopard Sightings'), (7, 'Elephant Herds'), (7, 'Bird Sanctuary'), (7, 'Safari Adventures'),
(8, 'Sacred Bodhi Tree'), (8, 'Ancient Dagobas'), (8, 'Moonstone Carvings'), (8, 'Royal Palaces'),
(9, 'Gal Vihara Buddha Statues'), (9, 'Ancient Irrigation Systems'), (9, 'Royal Palace Complex'), (9, 'Archaeological Museum'),
(10, 'Natural Harbor'), (10, 'Coral Reefs'), (10, 'Koneswaram Temple'), (10, 'Pigeon Island National Park');