disp("들림");
clear
Sr=1000; %sampling rate : times /sec
ts= Sr; %sampling time
N=50; % Sampling Number
T=ts *(N-1); % Sampling duration
t1=0:ts:T;
t2=0:ts/2:T;
t0=0:(ts*2):T;
f0=100; % signal frequency
w0=2*pi*f0; % signal angular freq.
y1 = sin(w0*t1); % Basic signal
y2 = sin(w0*t2); % Basic signal
y0 = sin(w0*t0); % Basic signal
f0=fft(y0);
f1=fft(y1);
f2=fft(y2);
figure(1)
subplot(4,2,1); plot(t2, y2); grid
subplot(4,2,3); stem(t0, y0); grid; subplot(4,2,4);
stem(abs(f0)); grid
subplot(4,2,5); stem(t1, y1); grid; subplot(4,2,6);
stem(abs(f1)); grid
subplot(4,2,7); stem(t2, y2); grid; subplot(4,2,8);
stem(abs(f2)); grid
