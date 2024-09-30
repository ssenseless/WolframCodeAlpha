import copy

import numpy as np
import copy as cp

from tkinter import *
from tkinter.ttk import Combobox
from PIL import Image, ImageTk
from automata import automata


def change_rule(event, x, y):
    if rule.get() != '':
        cell.rule = cell.set_rule(int(rule.get()))


def change_image(event):
    display = image_from_arr(image_choices[choice.get()])
    displaybel.image = display
    displaybel.config(image=display)

def update_row(curr_arr):
    new = np.array(cp.deepcopy(empty))
    for idx in range(len(curr_arr) - 1):
        curr_arr[idx + 1] = cell.compute_neighborhood(on_color, off_color, curr_arr[idx])

    for i in range(2):
        new = np.repeat(new, pixel_size, axis=i)
        curr_arr = np.repeat(curr_arr, pixel_size, axis=i)

    helper = Image.new(mode='RGB', size=(col_colnum * pixel_size, col_rownum * pixel_size))
    curr_arr = [tuple(tup) for lst in curr_arr.tolist() for tup in lst]
    new = [tuple(tup) for lst in new.tolist() for tup in lst]

    recursive_ev(
        idx=1,
        curr_arr=curr_arr,
        empty=new,
        helper=helper
    )

def recursive_ev(idx, curr_arr, empty, helper):
    global display

    d = curr_arr[:pixel_size * pixel_size * col_colnum * idx - 1]
    e = empty[pixel_size * pixel_size * col_colnum * idx - 1:]

    if idx <= col_rownum:
        helper.putdata(
            curr_arr[:pixel_size * pixel_size * col_colnum * idx - 1] +
            empty[pixel_size * pixel_size * col_colnum * idx - 1:]
        )

        display = ImageTk.PhotoImage(
            helper
        )
        displaybel.config(image=display)

        displaybel.after(3, recursive_ev, idx + 1, curr_arr, empty, helper)


def image_from_arr(curr_arr):
    image_np = np.array(curr_arr)

    image_np = np.repeat(image_np, pixel_size, axis=0)
    image_np = np.repeat(image_np, pixel_size, axis=1)

    return ImageTk.PhotoImage(Image.fromarray(np.uint8(image_np)))


def replace_indices(indices: list) -> ImageTk:
    for index in indices:
        image_arr[index[0]][index[1]] = (50, 168, 82)


# simple initializers and globals ------------------------------------------------------------
on_color = (50, 168, 82)
off_color = (65, 61, 74)
back_col = '#bdb19d'

cell = automata(0)
pixel_size = 2
col_colnum = 501
col_rownum = 204


root = Tk()
root.title('wolfram cellular automata visualizer')
root.config(bg=back_col)
root.geometry('1200x450')

# default choices
image_choices = {}
empty = [[(65, 61, 74) for j in range(col_colnum)] for i in range(col_rownum)]
for dots in range(1, 10):
    image_arr = cp.deepcopy(empty)

    for dot in range(1, dots + 1):
        image_arr[0][int((dot * (col_colnum - 1)) / (dots + 1))] = (50, 168, 82)

    image_choices.update({f"{dots} dot(s)": image_arr})

# pillowo ------------------------------------------------------------------------------------

display = image_from_arr(image_choices['1 dot(s)'])
displaybel = Label(image=display, borderwidth=0)

button = Button(
    root,
    text='Show automata',
    command=lambda: update_row(
        curr_arr=image_choices[choice.get()].copy()
    )
)

choice = StringVar()
choices = Combobox(root, textvariable=choice)
choices['state'] = 'readonly'
choices['values'] = list(image_choices.keys())
choices.bind('<<ComboboxSelected>>', change_image)
choices.current(0)
choice_label = Label(
    root,
    bg=back_col,
    text='Starting image:',
    font=('Arial', 10)
)

rule = StringVar()
rule.set('0')
rulebox = Entry(
    root,
    textvariable=rule
)
rule_label = Label(
    root,
    bg=back_col,
    text='Rule number:',
    font=('Arial', 10)
)
rule.trace('w', change_rule)

displaybel.grid(row=0, column=2, rowspan=5, pady=10)
button.grid(row=0, column=1, padx=10, pady=5)
choice_label.grid(row=1, column=1, padx=10, sticky='s')
choices.grid(row=2, column=1, padx=10, sticky='n')
rule_label.grid(row=3, column=1, padx=10, sticky='s')
rulebox.grid(row=4, column=1, padx=10, sticky='n')

# spacing on outsides
root.grid_columnconfigure((0, 3), weight=1)

if __name__ == "__main__":
    root.mainloop()


