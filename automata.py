def to_bytes_list(num: int) -> list:
    return [0 for _ in range(num.bit_length(), 8)] + [int(i) for i in bin(num)[2:]]


class automata:
    def __init__(self, rule: int) -> None:
        self.rule = self.set_rule(rule=rule)

    def compute_neighborhood(self, on_color: tuple, off_color: tuple, row: list) -> list:
        color_to_bin = {
            on_color: 1,
            off_color: 0
        }
        bin_to_color = {
            1: on_color,
            0: off_color
        }

        ret_row = []
        for i in range(len(row)):
            ret_row.append(
                bin_to_color[
                    self.rule[
                        int(
                            f"{color_to_bin[row[i - 1]]}{color_to_bin[row[i]]}{color_to_bin[row[(i + 1) % len(row)]]}",
                            2
                        )
                    ]
                ]
            )

        return ret_row


    def set_rule(self, rule: int) -> dict:
        return dict(zip([i for i in range(7, -1, -1)], to_bytes_list(rule)))


if __name__ == "__main__":
    cell = automata(30)
    print(cell.rule)
    print(to_bytes_list(4))
