clear;

pkg load signal

sing = audioread('sing.wav');
mr = audioread('back.wav');

length = min(size(sing),size(mr));
sing_r = sing(1:length,:);
mr_r = mr(1:length,:);
song = sing_r.+mr_r;

Fnyq = length(1)/2;
Fc = 200; %최고음이 200Hz 내외
[b,a] = butter(2, Fc/Fnyq);

subplot(211); stem(abs(fft(song)));
output = filter(b,a,song);
subplot(212); stem(abs(fft(output)));

audiowrite("filterSong.wav",output,44100);
[newSong,Fs] = audioread("filterSong.wav");
sound(newSong,Fs);
##[F, FL] = FFT(d(:,1),f);
##[val, idx] = max(abs(FL));
##printf("max : %d Hz => %f\n", idx, val);
##subplot(211); stem(F,abs(FL));
##
##[F, FL] = FFT(d1(:,1),f1);
##[val, idx] = max(abs(FL));
##printf("max : %d Hz => %f\n", idx, val);
##subplot(212); stem(F,abs(FL));
