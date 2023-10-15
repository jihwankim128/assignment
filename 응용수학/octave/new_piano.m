clear;

C = audioread('C.wav');
B = audioread('B.wav');

length = min(size(C),size(B));

melodyL = C(1:length, :);
melodyR = B(1:length, :);
newMeody = (melodyL+melodyR);
audiowrite("newMelody1.wav",newMeody,44100);
[newScale1,Fs1] = audioread("newMelody1.wav");
sound(newScale1,Fs1);
Fnyq = length(1)/2;

Fc=542;

[b,a]=butter(2,Fc/Fnyq,"high");

clear("Fnyq","Fc");
[F,FL] = FFT(newMeody(:,1),44100);
[val, idx] = max(abs(FL));
printf("%d, %f \n", idx, val);

t=0:(1.5)/length(1):(1.5)-(1.5/length(1));

output = filter(b,a,newMeody);

%figure(1)
%subplot(311); plot(1:length, melodyL);
%subplot(312); plot(1:length, melodyR);
%subplot(313); plot(newMeody);

%figure(2)
%subplot(311); stem(abs(fft(melodyL)));
%subplot(312); stem(abs(fft(melodyR)));
%subplot(313); stem(abs(fft(newMeody)));
figure(1)
subplot(211); plot(t, newMeody);
subplot(212); plot(t, output);

figure(2)
subplot(211); stem(abs(fft(newMeody)));
subplot(212); stem(abs(fft(output)));

audiowrite("newMelody.wav",output,44100);
[newScale,Fs] = audioread("newMelody.wav");
sound(newScale,Fs);

[F,FL] = FFT(output(:,1), 44100);
[val, idx] = max(abs(FL));
printf("%d, %f \n", idx, val);
