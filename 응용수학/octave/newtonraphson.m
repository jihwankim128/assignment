clear all
clc

x = 0.8;
for n = 1:10
  fprintf("---%d---\n", n);
  a = x^2-2*cos(x);
  b = 2*x+2*sin(x);
  fprintf("x_%d = ",n);
  disp(x);
  x=x-a/b;
  fprintf("x_%d = ",n+1);
  disp(x);
  fprintf("\n");
end
