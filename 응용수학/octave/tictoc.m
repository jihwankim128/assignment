clear all
for n=2:1000
a(n)=n;
tic;
A=rand(n);
B=rand(n);
C=A*B;
t(n) = toc;
end
plot(a,t);
xlim([-2 1010]);
ylim([-0.1 0.4]);
