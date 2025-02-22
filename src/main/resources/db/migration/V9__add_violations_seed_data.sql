INSERT INTO violations (title, description, deducted_points, created_at) VALUES
-- Vi phạm trong học tập
('Không uống hết nước trên trường', 'Không hoàn thành việc uống đủ nước trong ngày tại trường.', 30, CURRENT_TIMESTAMP),
('Không uống hết sữa trên trường', 'Không hoàn thành việc uống hết phần sữa được phát tại trường.', 30, CURRENT_TIMESTAMP),
('Đi học trễ', 'Đến lớp muộn mà không có lý do chính đáng.', 20, CURRENT_TIMESTAMP),
('Không làm bài tập', 'Không hoàn thành bài tập về nhà đúng hạn.', 20, CURRENT_TIMESTAMP),
('Không đi học mà không có lý do chính đáng', 'Vắng mặt ở trường mà không có sự cho phép của phụ huynh.', 20, CURRENT_TIMESTAMP),

-- Vi phạm về kỷ luật và sinh hoạt cá nhân
('Không dọn đồ chơi sau khi chơi', 'Bỏ bừa bộn đồ chơi mà không tự dọn dẹp.', 15, CURRENT_TIMESTAMP),
('Không đánh răng trước khi ngủ', 'Không tuân thủ việc đánh răng trước khi ngủ.', 20, CURRENT_TIMESTAMP),
('Không đánh răng khi đi ngủ', 'Không thực hiện vệ sinh răng miệng trước khi đi ngủ.', 20, CURRENT_TIMESTAMP),
('Không súc miệng khi đi ngủ', 'Không súc miệng bằng nước muối hoặc nước lọc trước khi đi ngủ.', 15, CURRENT_TIMESTAMP),
('Không rửa tay trước khi ăn', 'Không thực hiện vệ sinh tay trước khi ăn uống.', 15, CURRENT_TIMESTAMP),
('Không rửa tay', 'Không rửa tay sau khi đi vệ sinh.', 20, CURRENT_TIMESTAMP),

-- Vi phạm về hành vi và ứng xử
('Nói chuyện trong giờ học', 'Không giữ trật tự khi giáo viên giảng bài.', 20, CURRENT_TIMESTAMP),
('Chia sẻ thông tin cá nhân trên mạng', 'Chia sẻ thông tin cá nhân của mình trên mạng.', 30, CURRENT_TIMESTAMP),

-- Vi phạm về trách nhiệm cá nhân
('Không giúp bố mẹ khi được nhờ', 'Từ chối giúp đỡ bố mẹ làm việc nhà đơn giản.', 20, CURRENT_TIMESTAMP),
('Vứt rác bừa bãi', 'Không bỏ rác đúng nơi quy định.', 15, CURRENT_TIMESTAMP),
('Nằm xem điện thoại quá lâu', 'Sử dụng điện thoại quá thời gian quy định.', 30, CURRENT_TIMESTAMP),
('Không chịu ăn cơm', 'Không chịu ăn phần cơm đã được chuẩn bị.', 20, CURRENT_TIMESTAMP);
