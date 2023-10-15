%% Clear
clear all;
close all;
clc;

T = 2; %주기
f = 1/T; %주파수
ts = 0.001; % Sampling rate

t = 0:ts:T; % Time vector

A = 0;
B = 2;
f_t = zeros(size(t));
mid = find(t==T/2, 1); % 중앙 index
f_t(1:mid) = A; %양의값
f_t(mid+1:end) = B; %음의값

F_0 = 0.5 * (A + B); % a0를 나타냄
f2_t = F_0;

figure()
for n=1:1000
    F_n = (A-B) * (1-exp(-1i*pi*n)) / (1i*2*pi*n);
    f_nt = F_n .* exp(1i*2*pi*n*f*t);
    f2_t = f2_t + 2 * real(f_nt);

    plot(t, f_t);
    hold on
    plot(t, f2_t);
    axis([-0.1, T*1.1, min(A, B)-0.1, max(A, B)*1.2])
    grid on

    strN = num2str(n);
    str = strcat('n=[-', strN, ',', strN, ']');
    text(T/2, max(A, B)*1.1, str);

    text(T*0.55, A, 'A');
    text(T*0.45, B, 'B');

    pause(0.00001)
end
