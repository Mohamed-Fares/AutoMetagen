# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'seqHistogram.ui'
#
# Created: Fri Jul 16 21:41:21 2010
#      by: PyQt4 UI code generator 4.6.2
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

class Ui_SeqHistogramDialog(object):
    def setupUi(self, SeqHistogramDialog):
        SeqHistogramDialog.setObjectName("SeqHistogramDialog")
        SeqHistogramDialog.resize(441, 310)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Fixed, QtGui.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(SeqHistogramDialog.sizePolicy().hasHeightForWidth())
        SeqHistogramDialog.setSizePolicy(sizePolicy)
        icon = QtGui.QIcon()
        icon.addPixmap(QtGui.QPixmap("../../../../icons/programIcon.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        SeqHistogramDialog.setWindowIcon(icon)
        self.verticalLayout_5 = QtGui.QVBoxLayout(SeqHistogramDialog)
        self.verticalLayout_5.setObjectName("verticalLayout_5")
        self.horizontalLayout_5 = QtGui.QHBoxLayout()
        self.horizontalLayout_5.setObjectName("horizontalLayout_5")
        self.lblFieldToPlot = QtGui.QLabel(SeqHistogramDialog)
        self.lblFieldToPlot.setObjectName("lblFieldToPlot")
        self.horizontalLayout_5.addWidget(self.lblFieldToPlot)
        self.cboFieldToPlot = QtGui.QComboBox(SeqHistogramDialog)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Preferred, QtGui.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(1)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.cboFieldToPlot.sizePolicy().hasHeightForWidth())
        self.cboFieldToPlot.setSizePolicy(sizePolicy)
        self.cboFieldToPlot.setObjectName("cboFieldToPlot")
        self.cboFieldToPlot.addItem("")
        self.cboFieldToPlot.addItem("")
        self.horizontalLayout_5.addWidget(self.cboFieldToPlot)
        self.verticalLayout_5.addLayout(self.horizontalLayout_5)
        self.horizontalLayout_8 = QtGui.QHBoxLayout()
        self.horizontalLayout_8.setObjectName("horizontalLayout_8")
        self.groupBox_3 = QtGui.QGroupBox(SeqHistogramDialog)
        self.groupBox_3.setObjectName("groupBox_3")
        self.verticalLayout_3 = QtGui.QVBoxLayout(self.groupBox_3)
        self.verticalLayout_3.setObjectName("verticalLayout_3")
        self.formLayout_2 = QtGui.QFormLayout()
        self.formLayout_2.setObjectName("formLayout_2")
        self.lblFigureWidth = QtGui.QLabel(self.groupBox_3)
        self.lblFigureWidth.setObjectName("lblFigureWidth")
        self.formLayout_2.setWidget(0, QtGui.QFormLayout.LabelRole, self.lblFigureWidth)
        self.spinFigWidth = QtGui.QDoubleSpinBox(self.groupBox_3)
        self.spinFigWidth.setDecimals(2)
        self.spinFigWidth.setMinimum(2.0)
        self.spinFigWidth.setMaximum(20.0)
        self.spinFigWidth.setSingleStep(0.5)
        self.spinFigWidth.setProperty("value", 6.5)
        self.spinFigWidth.setObjectName("spinFigWidth")
        self.formLayout_2.setWidget(0, QtGui.QFormLayout.FieldRole, self.spinFigWidth)
        self.lblFigureHeight = QtGui.QLabel(self.groupBox_3)
        self.lblFigureHeight.setObjectName("lblFigureHeight")
        self.formLayout_2.setWidget(1, QtGui.QFormLayout.LabelRole, self.lblFigureHeight)
        self.spinFigHeight = QtGui.QDoubleSpinBox(self.groupBox_3)
        self.spinFigHeight.setMinimum(2.0)
        self.spinFigHeight.setMaximum(12.0)
        self.spinFigHeight.setSingleStep(0.5)
        self.spinFigHeight.setProperty("value", 6.5)
        self.spinFigHeight.setObjectName("spinFigHeight")
        self.formLayout_2.setWidget(1, QtGui.QFormLayout.FieldRole, self.spinFigHeight)
        self.verticalLayout_3.addLayout(self.formLayout_2)
        self.horizontalLayout_8.addWidget(self.groupBox_3)
        self.groupBox_2 = QtGui.QGroupBox(SeqHistogramDialog)
        self.groupBox_2.setObjectName("groupBox_2")
        self.verticalLayout_2 = QtGui.QVBoxLayout(self.groupBox_2)
        self.verticalLayout_2.setObjectName("verticalLayout_2")
        self.formLayout = QtGui.QFormLayout()
        self.formLayout.setObjectName("formLayout")
        self.lblSampleName1 = QtGui.QLabel(self.groupBox_2)
        self.lblSampleName1.setObjectName("lblSampleName1")
        self.formLayout.setWidget(0, QtGui.QFormLayout.LabelRole, self.lblSampleName1)
        self.txtSampleName1 = QtGui.QLineEdit(self.groupBox_2)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(1)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.txtSampleName1.sizePolicy().hasHeightForWidth())
        self.txtSampleName1.setSizePolicy(sizePolicy)
        self.txtSampleName1.setObjectName("txtSampleName1")
        self.formLayout.setWidget(0, QtGui.QFormLayout.FieldRole, self.txtSampleName1)
        self.lblSampleName2 = QtGui.QLabel(self.groupBox_2)
        self.lblSampleName2.setObjectName("lblSampleName2")
        self.formLayout.setWidget(1, QtGui.QFormLayout.LabelRole, self.lblSampleName2)
        self.txtSampleName2 = QtGui.QLineEdit(self.groupBox_2)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(1)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.txtSampleName2.sizePolicy().hasHeightForWidth())
        self.txtSampleName2.setSizePolicy(sizePolicy)
        self.txtSampleName2.setObjectName("txtSampleName2")
        self.formLayout.setWidget(1, QtGui.QFormLayout.FieldRole, self.txtSampleName2)
        self.verticalLayout_2.addLayout(self.formLayout)
        self.horizontalLayout_8.addWidget(self.groupBox_2)
        self.verticalLayout_5.addLayout(self.horizontalLayout_8)
        self.horizontalLayout = QtGui.QHBoxLayout()
        self.horizontalLayout.setObjectName("horizontalLayout")
        self.groupBox_4 = QtGui.QGroupBox(SeqHistogramDialog)
        self.groupBox_4.setObjectName("groupBox_4")
        self.verticalLayout_4 = QtGui.QVBoxLayout(self.groupBox_4)
        self.verticalLayout_4.setObjectName("verticalLayout_4")
        self.chkCustomBinWidth = QtGui.QCheckBox(self.groupBox_4)
        self.chkCustomBinWidth.setObjectName("chkCustomBinWidth")
        self.verticalLayout_4.addWidget(self.chkCustomBinWidth)
        self.horizontalLayout_7 = QtGui.QHBoxLayout()
        self.horizontalLayout_7.setObjectName("horizontalLayout_7")
        self.lblBinWidth = QtGui.QLabel(self.groupBox_4)
        self.lblBinWidth.setObjectName("lblBinWidth")
        self.horizontalLayout_7.addWidget(self.lblBinWidth)
        self.horizontalLayout_2 = QtGui.QHBoxLayout()
        self.horizontalLayout_2.setObjectName("horizontalLayout_2")
        self.spinBinWidth = QtGui.QDoubleSpinBox(self.groupBox_4)
        self.spinBinWidth.setMaximum(1000.0)
        self.spinBinWidth.setSingleStep(0.1)
        self.spinBinWidth.setObjectName("spinBinWidth")
        self.horizontalLayout_2.addWidget(self.spinBinWidth)
        spacerItem = QtGui.QSpacerItem(40, 20, QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Minimum)
        self.horizontalLayout_2.addItem(spacerItem)
        self.horizontalLayout_7.addLayout(self.horizontalLayout_2)
        self.verticalLayout_4.addLayout(self.horizontalLayout_7)
        self.line = QtGui.QFrame(self.groupBox_4)
        self.line.setFrameShape(QtGui.QFrame.HLine)
        self.line.setFrameShadow(QtGui.QFrame.Sunken)
        self.line.setObjectName("line")
        self.verticalLayout_4.addWidget(self.line)
        self.chkLogScale = QtGui.QCheckBox(self.groupBox_4)
        self.chkLogScale.setObjectName("chkLogScale")
        self.verticalLayout_4.addWidget(self.chkLogScale)
        self.horizontalLayout.addWidget(self.groupBox_4)
        self.groupBox = QtGui.QGroupBox(SeqHistogramDialog)
        self.groupBox.setObjectName("groupBox")
        self.verticalLayout = QtGui.QVBoxLayout(self.groupBox)
        self.verticalLayout.setObjectName("verticalLayout")
        self.horizontalLayout_9 = QtGui.QHBoxLayout()
        self.horizontalLayout_9.setObjectName("horizontalLayout_9")
        self.lblTitle = QtGui.QLabel(self.groupBox)
        self.lblTitle.setObjectName("lblTitle")
        self.horizontalLayout_9.addWidget(self.lblTitle)
        self.txtXLabel = QtGui.QLineEdit(self.groupBox)
        self.txtXLabel.setObjectName("txtXLabel")
        self.horizontalLayout_9.addWidget(self.txtXLabel)
        self.verticalLayout.addLayout(self.horizontalLayout_9)
        self.chkCustomXaxis = QtGui.QCheckBox(self.groupBox)
        self.chkCustomXaxis.setObjectName("chkCustomXaxis")
        self.verticalLayout.addWidget(self.chkCustomXaxis)
        self.formLayout_3 = QtGui.QFormLayout()
        self.formLayout_3.setObjectName("formLayout_3")
        self.label_3 = QtGui.QLabel(self.groupBox)
        self.label_3.setObjectName("label_3")
        self.formLayout_3.setWidget(0, QtGui.QFormLayout.LabelRole, self.label_3)
        self.horizontalLayout_6 = QtGui.QHBoxLayout()
        self.horizontalLayout_6.setObjectName("horizontalLayout_6")
        self.spinXmin = QtGui.QDoubleSpinBox(self.groupBox)
        self.spinXmin.setDecimals(0)
        self.spinXmin.setMinimum(0.0)
        self.spinXmin.setMaximum(10000.0)
        self.spinXmin.setSingleStep(10.0)
        self.spinXmin.setProperty("value", 0.0)
        self.spinXmin.setObjectName("spinXmin")
        self.horizontalLayout_6.addWidget(self.spinXmin)
        self.btnXmin = QtGui.QPushButton(self.groupBox)
        self.btnXmin.setObjectName("btnXmin")
        self.horizontalLayout_6.addWidget(self.btnXmin)
        self.formLayout_3.setLayout(0, QtGui.QFormLayout.FieldRole, self.horizontalLayout_6)
        self.label = QtGui.QLabel(self.groupBox)
        self.label.setObjectName("label")
        self.formLayout_3.setWidget(1, QtGui.QFormLayout.LabelRole, self.label)
        self.horizontalLayout_4 = QtGui.QHBoxLayout()
        self.horizontalLayout_4.setObjectName("horizontalLayout_4")
        self.spinXmax = QtGui.QDoubleSpinBox(self.groupBox)
        self.spinXmax.setDecimals(0)
        self.spinXmax.setMaximum(10000.0)
        self.spinXmax.setSingleStep(10.0)
        self.spinXmax.setProperty("value", 1.0)
        self.spinXmax.setObjectName("spinXmax")
        self.horizontalLayout_4.addWidget(self.spinXmax)
        self.btnXmax = QtGui.QPushButton(self.groupBox)
        self.btnXmax.setObjectName("btnXmax")
        self.horizontalLayout_4.addWidget(self.btnXmax)
        self.formLayout_3.setLayout(1, QtGui.QFormLayout.FieldRole, self.horizontalLayout_4)
        self.verticalLayout.addLayout(self.formLayout_3)
        self.horizontalLayout.addWidget(self.groupBox)
        self.verticalLayout_5.addLayout(self.horizontalLayout)
        self.horizontalLayout_3 = QtGui.QHBoxLayout()
        self.horizontalLayout_3.setObjectName("horizontalLayout_3")
        spacerItem1 = QtGui.QSpacerItem(168, 20, QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Minimum)
        self.horizontalLayout_3.addItem(spacerItem1)
        self.buttonBox = QtGui.QDialogButtonBox(SeqHistogramDialog)
        self.buttonBox.setOrientation(QtCore.Qt.Horizontal)
        self.buttonBox.setStandardButtons(QtGui.QDialogButtonBox.Cancel|QtGui.QDialogButtonBox.Ok)
        self.buttonBox.setCenterButtons(False)
        self.buttonBox.setObjectName("buttonBox")
        self.horizontalLayout_3.addWidget(self.buttonBox)
        self.verticalLayout_5.addLayout(self.horizontalLayout_3)

        self.retranslateUi(SeqHistogramDialog)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL("accepted()"), SeqHistogramDialog.accept)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL("rejected()"), SeqHistogramDialog.reject)
        QtCore.QMetaObject.connectSlotsByName(SeqHistogramDialog)

    def retranslateUi(self, SeqHistogramDialog):
        SeqHistogramDialog.setWindowTitle(QtGui.QApplication.translate("SeqHistogramDialog", "Configure plot", None, QtGui.QApplication.UnicodeUTF8))
        self.lblFieldToPlot.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Field to plot:", None, QtGui.QApplication.UnicodeUTF8))
        self.cboFieldToPlot.setItemText(0, QtGui.QApplication.translate("SeqHistogramDialog", "Sequences", None, QtGui.QApplication.UnicodeUTF8))
        self.cboFieldToPlot.setItemText(1, QtGui.QApplication.translate("SeqHistogramDialog", "Parental sequences", None, QtGui.QApplication.UnicodeUTF8))
        self.groupBox_3.setTitle(QtGui.QApplication.translate("SeqHistogramDialog", "Figure size", None, QtGui.QApplication.UnicodeUTF8))
        self.lblFigureWidth.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Width:", None, QtGui.QApplication.UnicodeUTF8))
        self.lblFigureHeight.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Height:", None, QtGui.QApplication.UnicodeUTF8))
        self.groupBox_2.setTitle(QtGui.QApplication.translate("SeqHistogramDialog", "Sample names", None, QtGui.QApplication.UnicodeUTF8))
        self.lblSampleName1.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Sample name 1:", None, QtGui.QApplication.UnicodeUTF8))
        self.lblSampleName2.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Sample name 2:", None, QtGui.QApplication.UnicodeUTF8))
        self.groupBox_4.setTitle(QtGui.QApplication.translate("SeqHistogramDialog", "Histogram properties", None, QtGui.QApplication.UnicodeUTF8))
        self.chkCustomBinWidth.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Custom bin width", None, QtGui.QApplication.UnicodeUTF8))
        self.lblBinWidth.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Bin width:", None, QtGui.QApplication.UnicodeUTF8))
        self.chkLogScale.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Show y-axis as log scale", None, QtGui.QApplication.UnicodeUTF8))
        self.groupBox.setTitle(QtGui.QApplication.translate("SeqHistogramDialog", "X-axis properties", None, QtGui.QApplication.UnicodeUTF8))
        self.lblTitle.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Title:", None, QtGui.QApplication.UnicodeUTF8))
        self.chkCustomXaxis.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Custom x-axis extents", None, QtGui.QApplication.UnicodeUTF8))
        self.label_3.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Min. value:", None, QtGui.QApplication.UnicodeUTF8))
        self.btnXmin.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Min", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Max value:", None, QtGui.QApplication.UnicodeUTF8))
        self.btnXmax.setText(QtGui.QApplication.translate("SeqHistogramDialog", "Max", None, QtGui.QApplication.UnicodeUTF8))

