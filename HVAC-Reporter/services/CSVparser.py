import csv

# Global variable
final_data = []


def get_data():

    data = prepare()
    return data


def prepare():

    csv_file = open('/home/linuxsagar/IdeaProjects/HVAC-MapReduce/HVAC-Reporter/services/data/part-r-00000.csv', 'r')
    reader = csv.reader(csv_file)
    reader.next()
    for row in reader:
        bid = int(row[0])
        model = row[1]
        exp = float(row[2])
        act = float(row[3])
        final_data.append({"bid":bid, "model":model, "expected":exp, "actual":act})
    return final_data

if __name__ == '__main__':
    prepare()