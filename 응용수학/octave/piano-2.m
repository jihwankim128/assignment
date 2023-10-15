disp("들림");
C = audioread('C.wav');
B = audioread('B.wav');

length = min(size(C),size(B));
