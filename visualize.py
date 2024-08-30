from tkinter import *
import numpy as np
from PIL import Image, ImageTk
from automata import automata


def update_row(index):
    global display
    if index < (len(image_arr) - 1):
        image_arr[index + 1] = cell.compute_neighborhood(on_color, off_color, image_arr[index])

        display = image_from_arr()
        displaybel.config(image=display)

        displaybel.after(1, update_row, index + 1)


def image_from_arr():
    image_np = np.array(image_arr)

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

cell = automata(182)
pixel_size = 3
col_colnum = 401
col_rownum = 160

image_arr = [[(65, 61, 74) for j in range(col_colnum)] for i in range(col_rownum)]
image_arr[0][int((col_colnum - 1) / 2)] = (50, 168, 82)

root = Tk()
root.title('k-means image compression')
root.config(bg=back_col)
root.state('zoomed')

# pillowo ------------------------------------------------------------------------------------

display = image_from_arr()
displaybel = Label(image=display, borderwidth=0)
displaybel.grid(row=0, column=1)

button = Button(root, text='go', command=lambda: update_row(0))
button.grid(row=0, column=0)

# spacing on outsides
root.grid_columnconfigure((0, 2), weight=1)

if __name__ == "__main__":
    root.mainloop()


