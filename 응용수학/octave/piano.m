%clear
clear all; close all; clc;

%% scale 음원
c = "C.wav"; b = "B.wav";
## e = "E.wav"; f = "F.wav";
##g = "G.wav"; a = "A.wav"; d = "D.wav";

% data, Sampling Rate
[scale_C, Cr]=audioread(c); [scale_B, Br]=audioread(b);
##[scale_E, Er]=audioread(e); [scale_F, Fr]=audioread(f);
##[scale_G, Gr]=audioread(g); [scale_A, Ar]=audioread(a);
##[scale_D, Dr]=audioread(d);
len_c = length(scale_C);
fc = fft(scale_C,len_c/2);
len_b = length(scale_B);
fb = fft(scale_B,len_b/2);

len = min(length(fb),length(fc));
fn = zeros(len,1);
for i = 1:len
  fn(i)=fb(i)+fc(i);
end
audiowrite('melody.wav',ifft(fn,len*2),Cr);
[scale_N,Nr] = audioread("melody.wav");
sound(scale_N,Nr);
Fnyq = length(scale_N)/2;
Fc = 1000;
[b,a] = butter(2, Fc/Fnyq);
clear("Fnyq", "Fc");
input = fn;
output = filter(b,a,input);
%daf=audioplayer(scale_B,Br)
%das=audioplayer(scale_N,Nr)
%subplot(511); plot(ifft(fn,len*2));
%subplot(512); plot(scale_B);
%subplot(513); stem(abs(fn));
%subplot(514); stem(fb);
%subplot(515); stem(fc);

figure(2);
subplot(211); stem(abs(input));
subplot(212); stem(abs(output));



audiowrite('filter.wav',ifft(output,len*2),Cr);
[filter_N,Nf] = audioread("filter.wav");
sound(filter_N,Nf);
%% fn을 fc와 fb의 합성과 비교

##len_c = length(scale_C); % data양
##fc = fft(scale_C,len_c/2); %채널이 두개이므로 나누기 2
##subplot(311); stem(abs(fc)); grid on %fft로 인한 허수 부분을 없애기 위해 abs
##
##len_b = length(scale_B);
##fb = fft(scale_B,len_b/2);
##subplot(312); stem(abs(fb)); grid on
##
##subplot(313); stem(abs(fc+fb));



##len_e = length(scale_E);
##fe = fft(scale_E,len_e/2);
##figure(3); stem(abs(fe)); grid on
##
##len_f = length(scale_F);
##ff = fft(scale_F,len_f/2);
##figure(4); stem(abs(ff)); grid on
##
##len_g = length(scale_G);
##fg = fft(scale_G,len_g/2);
##figure(5); stem(abs(fg)); grid on
##
##len_a = length(scale_A);
##fa = fft(scale_A,len_a/2);
##figure(6); stem(abs(fa)); grid on
##
##len_b = length(scale_B);
##fb = fft(scale_B,len_b/2);
##figure(7); stem(abs(fb)); grid on
