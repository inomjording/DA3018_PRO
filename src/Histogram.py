import matplotlib.pyplot as plt
import numpy as np


def file_reader(file_name):
    data_list = list()
    with open(file_name) as file:
        for line in file:
            data_list.append(line[1])
    return data_list


def plotter(data_list):
    plt.hist(data_list)
    plt.title("Degree Distribution")
    plt.savefig("degree_distribution.pdf")


def main():
    plotter(file_reader("distribution.txt"))


if __name__ == '__main__':
    main()
