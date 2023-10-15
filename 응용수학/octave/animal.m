[tiger,Fs_t] = audioread("tiger.wav");
[F, FL] = FFT(tiger(:,1),Fs_t);
[val, idx] = min(abs(FL));
printf("Tiger, min : %d Hz => %f\n", idx, val);
subplot(411); plot(abs(fftshift(fft(tiger))));

[alligator,Fs_a] = audioread("alligator.wav");
[F, FL] = FFT(alligator(:,1),Fs_a);
[val, idx] = min(abs(FL));
printf("Alligator, min : %d Hz => %f\n", idx, val);
subplot(412); plot(abs(fftshift(fft(alligator))));

[dolphin,Fs_d] = audioread("dolphin.wav");
[F, FL] = FFT(dolphin(:,1),Fs_d);
[val, idx] = min(abs(FL));
printf("Dolphin, min : %d Hz => %f\n", idx, val);
subplot(413); plot(abs(fftshift(fft(dolphin))));

[bat,Fs_b] = audioread("bat.wav");
[F, FL] = FFT(bat(:,1),Fs_b);
[val, idx] = min(abs(FL));
printf("Bat, min : %d Hz => %f\n", idx, val);
subplot(414); plot(abs(fftshift(fft(bat))));

