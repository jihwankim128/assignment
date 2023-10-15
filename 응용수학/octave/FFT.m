function [ freq, X ] = FFT (x,Sr)
  N=length(x);
  k=0:N-1;
  T=N/Sr;
  freq=k/T;
  X=fft(x)/N*2;
  cutOff=ceil(N/2);
  X=X(1:cutOff);
  freq=freq(1:cutOff);
endfunction

