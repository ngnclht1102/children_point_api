CREATE TABLE challenges (
   id SERIAL PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   description TEXT,
   earned_points INT NOT NULL,
   created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO challenges (title, description, earned_points, created_at) VALUES
  ('Ăn rau', 'Hoàn thành việc phần rau ba mẹ giao.', 50, CURRENT_TIMESTAMP),
  ('Ăn canh', 'Hoàn thành bát canh trong bữa ăn.', 20, CURRENT_TIMESTAMP),
  ('Đi ngủ trưa', 'Đi ngủ trưa đúng giờ và đủ giấc.', 100, CURRENT_TIMESTAMP),
  ('Đọc 1 trang sách', 'Đọc hết 1 trang sách bất kỳ.', 10, CURRENT_TIMESTAMP),
  ('Đọc 2 trang sách', 'Đọc liền 2 trang sách bất kỳ.', 20, CURRENT_TIMESTAMP),
  ('Đọc 1 câu chuyện', 'Hoàn thành việc đọc 1 câu chuyện ngắn.', 30, CURRENT_TIMESTAMP),
  ('Đọc 1 câu chuyện và kể lại cho ai đó ', 'Hoàn thành việc đọc 1 câu chuyện ngắn và kể lại cho ai đó.', 100, CURRENT_TIMESTAMP),
  ('Đánh đàn 15 phút', 'Luyện tập đánh đàn liên tục trong 15 phút.', 35, CURRENT_TIMESTAMP),
  ('Đánh đàn 30 phút', 'Luyện tập đánh đàn không nghỉ trong 30 phút.', 50, CURRENT_TIMESTAMP),
  ('Hoàn thành 1 robot', 'Hoàn thành 1 robot, bao gồm phần lập trình trên ipad .', 60, CURRENT_TIMESTAMP);


INSERT INTO challenges (title, description, earned_points, created_at) VALUES
  ('Ăn hết 1 chén cơm', 'Hoàn thành việc ăn hết 1 chén cơm trong bữa ăn.', 20, CURRENT_TIMESTAMP),
  ('Ăn hết 2 chén cơm', 'Hoàn thành việc ăn hết 2 chén cơm trong bữa ăn.', 40, CURRENT_TIMESTAMP),
  ('Gấp mền gối', 'Tự gấp gọn gàng mền gối sau khi ngủ dậy.', 25, CURRENT_TIMESTAMP),
  ('Tự giặt đồ và phơi đồ', 'Tự giặt quần áo và phơi đồ đúng cách.', 50, CURRENT_TIMESTAMP),
  ('Tự tắm', 'Tự tắm sạch sẽ mà không cần nhắc nhở.', 50, CURRENT_TIMESTAMP),
  ('Tự gội đầu', 'Tự gội đầu sạch sẽ mà không cần hỗ trợ.', 55, CURRENT_TIMESTAMP);

INSERT INTO challenges (title, description, earned_points, created_at) VALUES
  ('Dọn bàn học', 'Hoàn thành việc dọn dẹp bàn học phòng sạch sẽ và gọn gàng.', 50, CURRENT_TIMESTAMP),
  ('Dọn phòng gọn gàng', 'Hoàn thành việc dọn dẹp phòng sạch sẽ và gọn gàng.', 50, CURRENT_TIMESTAMP),
  ('Đọc sách 30 phút', 'Đọc sách liên tục trong 30 phút mà không bị phân tâm.', 30, CURRENT_TIMESTAMP),
  ('Giúp bố mẹ nấu ăn', 'Tham gia vào việc chuẩn bị bữa ăn cùng bố mẹ.', 40, CURRENT_TIMESTAMP),
  ('Làm bài tập', 'Hoàn thành tất cả bài tập về nhà đúng hạn.', 60, CURRENT_TIMESTAMP),
  ('Đi học Apollo', 'Đi học tiếng anh.', 20, CURRENT_TIMESTAMP),
  ('Đi học Đàn', 'Đi học tiếng anh.', 20, CURRENT_TIMESTAMP),
  ('Làm 1 Quiz', 'Hoàn thành tất cả Quiz.', 10, CURRENT_TIMESTAMP),
  ('Chạy bộ 1 km', 'Thực hiện chạy bộ hoàn thành quãng đường 1 km.', 70, CURRENT_TIMESTAMP),
  ('Tưới cây trong vườn', 'Tưới nước cho tất cả cây trong vườn mà không bỏ sót.', 20, CURRENT_TIMESTAMP),
  ('Gấp quần áo gọn gàng', 'Tự gấp quần áo sau khi giặt mà không cần nhắc nhở.', 35, CURRENT_TIMESTAMP),
  ('Đánh răng đúng cách', 'Đánh răng đều đặn sáng tối và đúng kỹ thuật.', 25, CURRENT_TIMESTAMP),
  ('Không dùng điện thoại 1 giờ', 'Tránh xa các thiết bị điện tử trong 1 giờ.', 45, CURRENT_TIMESTAMP),
  ('Học thuộc lòng một bài thơ', 'Thuộc lòng và đọc trôi chảy một bài thơ của bà nội mà không cần nhìn sách.', 95, CURRENT_TIMESTAMP);
